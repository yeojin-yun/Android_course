package com.example.recipeappstudy

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    //viewModel 선언
    val viewModel: MainViewModel = viewModel()
    val viewState = viewModel.categoriesState.value
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(viewState) {
                navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                navController.navigate(Screen.DetailScreen.route)
            }
        }

        composable(Screen.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("category") ?: Category(id = -1, description = "", image = null, name="", subcategories = emptyList())
            SubcategoryScreen(subCategory = category.subcategories)
        }
    }
}