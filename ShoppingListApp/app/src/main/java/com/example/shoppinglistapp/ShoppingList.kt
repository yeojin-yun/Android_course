package com.example.shoppinglistapp

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.example.locationapp.LocationUtils

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false,
    var address: String = ""
    )

@ExperimentalMaterial3Api
@Composable
fun ShoppingListApp(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    navHostController: NavHostController,
    context: Context,
    address: String
) {
    //쇼핑 리스트를 담을 변수
    var shoppingItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    //얼럿 띄울지 말지 변수
    var showDialog by remember { mutableStateOf(false) }
    //쇼핑 아이템 이름
    var itemName by remember { mutableStateOf("") }
    //쇼핑 아이템 수량
    var itemQuantity by remember { mutableStateOf("") }
    //권한 런처 요청
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permission ->
        if(permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            &&
            permission[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            //I Have Access to location
            locationUtils.requestLocationUpdate(viewModel)
        } else {
            //I Have To Request Permission For Location
            //why we need the permission
            val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                context as MainActivity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) || ActivityCompat.shouldShowRequestPermissionRationale(
                context as MainActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            if (rationalRequired) {
                Toast.makeText(context, "Location Permission is required for this feature to work", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Location Permission is required. Please enable to permision", Toast.LENGTH_LONG).show()
            }
        }
    }

    //UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        verticalArrangement = Arrangement.Center, //세로 정렬
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //쇼핑 아이템 추가 버튼
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),

        ) {
            Text(text = "Add Item")
        }
        //쇼핑 리스트
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            ) {
            items(shoppingItems){
                item ->
                if (item.isEditing) {
                    ShoppingItemEdit(item = item) {
                        editedName, editedQuantity ->
                        shoppingItems = shoppingItems.map {
                            if (it.id == item.id) it.copy(name = editedName, quantity = editedQuantity, isEditing = false, address = address)
                            else it.copy(isEditing = false)
                        }
                    }
                } else {
                    ShoppingListItem(item = item, onEditClick = {
                        shoppingItems = shoppingItems.map { it.copy(isEditing = it.id == item.id) }
                    }, onDeleteClick = {
                        shoppingItems = shoppingItems.filter { it.id != item.id }
                    })
                }
            }
        }
    }

    //얼럿 띄울 조건문
    if (showDialog) {
    BasicAlertDialog(
        //얼럿 띄운 뒤 호출될 콜백 메서드
        onDismissRequest = {
            showDialog = false
        }
    ) {
        //얼럿 UI
        Column {
            //제목
            Text("Add Shopping Item")
            //아이템 이름 입력 텍스트 필드
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = itemName,
                onValueChange = {itemName=it}
            )
            //아이템 수량 입력 텍스트 필드
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = itemQuantity,
                onValueChange = {itemQuantity=it}
            )
            Button(onClick = {
                if (locationUtils.hasLocationPermission(context)) {
                    locationUtils.requestLocationUpdate(viewModel)
                    navHostController.navigate("locationscreen") {
                        this.launchSingleTop
                    }
                } else {
                    requestPermissionLauncher.launch(arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ))
                }
            }) {
                Text("select location")
            }
            //버튼 Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //1번 버튼-취소
                Button(onClick = {showDialog=false}) {
                    Text("Cancel")
                }
                //2번 버튼-추가
                Button(onClick = {
                    //아이템 이름이 입력되어 있으면 shoppingItem 리스트에 해당 아이템을 추가해야 함
                    if (itemName.isNotBlank()) {
                        val newItem = ShoppingItem(id=shoppingItems.size+1, name = itemName, quantity = itemQuantity.toInt(), isEditing = false, address = address)
                        shoppingItems = shoppingItems + newItem
                        showDialog = false
                        itemName = ""
                        itemQuantity = ""
                    }
                }) {
                    Text("Add")
                }
            }
        }
    }
    }
}

//쇼핑 아이템 UI
@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color.Green),
                shape = RoundedCornerShape(20)
            ),

    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            Row {
                Text(text = item.name, modifier = Modifier.padding(8.dp))
                Text(text = item.quantity.toString(), modifier = Modifier.padding(8.dp))
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(text = item.address)
            }
        }

        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, null)
            }
        }

    }
}

@Composable
fun ShoppingItemEdit(item: ShoppingItem, editCompleted: (String, Int) -> Unit) {
    //아이템 이름
    var itemName by remember { mutableStateOf(item.name) }
    //아이템 수량
    var itemQuantity by remember { mutableStateOf(item.quantity.toString()) }
    //아이템 편집 여부
    var isEditing by remember { mutableStateOf(item.isEditing) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
    ) {
        Column {
            BasicTextField(value = itemName, onValueChange = {itemName=it})
            BasicTextField(value = itemQuantity, onValueChange = {itemQuantity = it})
        }
        Button(onClick = {
            isEditing = false
            editCompleted(itemName, itemQuantity.toIntOrNull() ?: 1)
        }) {
            Text("Save")
        }
    }
}