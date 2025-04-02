package com.example.wishlistapp

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wishlistapp.data.DummyList

@Composable
fun HomeView(
    navHostController: NavHostController,
    viewModel: WishViewModel
) {
    val context = LocalContext.current
    Scaffold(
        topBar = { AppBarView(title = "Wish List", onBackNavClicked = {
            Toast.makeText(context,"Text", Toast.LENGTH_LONG).show()
        }) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                backgroundColor = colorResource(id = R.color.black),
                contentColor = colorResource(id = R.color.white),
                onClick = {
                    navHostController.navigate(Screen.AddScreen.route + "/0L")
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add icon")
            }
        }
    ) { padding ->
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            items(wishList.value) {
                WishItem(item = it) {
                    navHostController.navigate(Screen.AddScreen.route+ "/${it.id}")
                }
            }
        }
    }
}