package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier, viewState:MainViewModel.RecipeState,navigateDetail: (Category) -> Unit) {
    val recipeViewModel: MainViewModel = viewModel()

    
    Box(modifier = Modifier.fillMaxSize()) {
        when{
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error !=null -> {
                Text(text = "ERROR OCCURED")
            }
            else -> {
                //Display Data
                CategoryScreen(categories = viewState.list, navigateDetail)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateDetail: (Category) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), 
        modifier = Modifier.fillMaxSize()
    ) {
        items(categories) {
            category -> CategoryItem(category = category, navigateDetail)
        }
    }
}

@Composable 
fun CategoryItem(category: Category, navigateDetail: (Category) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize().clickable {
                navigateDetail(category)
            }

    ) {
        //이미지

        Image(
            painter = rememberAsyncImagePainter(
                model = category.strCategoryThumb,
//                error = painterResource(id = R.drawable.ic_error), // 실패 시 표시할 이미지
//                placeholder = painterResource(id = R.drawable.ic_placeholder) // 로딩 중 표시할 이미지
            ),
            contentDescription = "category image",
            modifier = Modifier.size(100.dp)
        )
        
        //카테고리 이름
        Text(
            category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }
}