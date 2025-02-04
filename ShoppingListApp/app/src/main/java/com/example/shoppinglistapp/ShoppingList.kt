package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(val id: Int, val name: String, val quantity: Int, val isEditing: Boolean = false)

@ExperimentalMaterial3Api
@Composable
fun ShoppingListItem() {
    var shoppingItem by remember {
        mutableStateOf(listOf<ShoppingItem>())
    }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center //세로 정렬
    ) {
        Button(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            ) {
            items(shoppingItem){}
        }
    }

    if (showDialog) {
//        AlertDiaBlog(onDismissRequest = {showDialog =false},content = { Text("content") })
    BasicAlertDialog(onDismissRequest = {showDialog =false}) {

        Column {
            Text("Add Shopping Item")
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                value = itemName,
                onValueChange = {itemName=it}
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                value = itemQuantity,
                onValueChange = {itemQuantity=it}
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {showDialog=false}) {
                    Text("Cancel")
                }
                Button(onClick = {
                    if (itemName.isNotBlank()) {
                        val newItem = ShoppingItem(id=shoppingItem.size+1, name = itemName, quantity = itemQuantity.toInt(), isEditing = false)
                        shoppingItem = shoppingItem + newItem
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
