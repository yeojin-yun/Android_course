package com.example.recipeappstudy

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(
    viewState: MainViewModel.RecipeState,
    navigationDetail: (Category) -> Unit
) {
    println("viewState $viewState")
    //composable
    Box(modifier = Modifier.fillMaxSize()) {
        when{
            viewState.isLoading -> {
                CircularProgressIndicator(color = Color.Yellow)
            }
            viewState.errorMessage != null -> {
                Text("Fail to Load Category data")
            }

            viewState.list.isNotEmpty() -> {
                CategoryScreen(categories = viewState.list, navigationDetail)
            }
        }
    }
}

@Composable
fun CategoryScreen(
    categories: List<Category>,
    navigationDetail: (Category) -> Unit
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories) {
            category -> CategoryItem(category = category, navigationDetail)
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    navigationDetail: (Category) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().clickable {
            navigationDetail(category)
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = category.image),
            modifier = Modifier.size(100.dp),
            contentDescription = "${category.name} image"
        )
        Text(text = category.name)
    }
}