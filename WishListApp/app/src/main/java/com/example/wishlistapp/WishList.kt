package com.example.wishlistapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.wishlistapp.data.DummyList
import com.example.wishlistapp.data.Wish

@Composable
fun WishList(
    navHostController: NavHostController,
    padding: PaddingValues,

) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(padding)) {
        val dummyList = DummyList.wishList
        items(dummyList) {
            WishItem(item = it) {
                navHostController.navigate(Screen.AddScreen.route)
            }
        }
    }
}

@Composable
fun WishItem(
    item: Wish,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(text = item.title, fontWeight = FontWeight.Bold)
            Text(text = item.description, fontWeight = FontWeight.Normal)
        }
    }
}