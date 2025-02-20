package com.example.recipeappstudy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen() {
    //viewModel 선언
    val viewModel: MainViewModel = viewModel()
    val viewState = viewModel.categoriesState.value
    //composable
    Box(modifier = Modifier.fillMaxSize()) {
        when{
            viewState.isLoading -> {
                CircularProgressIndicator()
            }
            viewState.errorMessage != null -> {
                Text("Fail to Load Category data")
            }

            viewState.list.isNotEmpty() -> {
                CategoryScreen(categories = viewState.list)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories) {
            category -> CategoryItem(category = category)
        }
    }
}

@Composable
fun CategoryItem(category: Category) {
    Column {
        Image(painter = rememberAsyncImagePainter(model = category.image), contentDescription = "${category.name} image")
        Text(text = category.name)
    }
}