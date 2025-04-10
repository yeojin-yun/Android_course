package com.example.musicapp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BrowseView() {
    val categories = listOf("apple", "banana", "sleep", "iPhone", "night", "puppy")
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories) { category -> 
           BrowseItem(category = category)
        }
    }
}

@Composable
fun BrowseItem(category: String) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(100.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = category)
        }
    }
}