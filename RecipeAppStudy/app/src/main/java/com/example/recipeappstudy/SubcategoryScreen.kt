package com.example.recipeappstudy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun SubcategoryScreen(subCategory: List<SubCategory>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(subCategory)  {
            subCategory -> SubCategoryItem(subCategory = subCategory)
        }
    }
}

@Composable
fun SubCategoryItem(subCategory: SubCategory) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = rememberAsyncImagePainter(model = subCategory.image),
            modifier = Modifier.size(100.dp),
            contentDescription = "${subCategory.name} image"
        )
        Text(text = subCategory.name)
    }
}