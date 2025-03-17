package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Naviagtion(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
       composable(Screen.HomeScreen.route) {
           HomeView(navController, viewModel)
       }
        composable(Screen.AddScrreen.route) {
            AddEditDetailView(id = 0L, viewModel=viewModel, navHostController=navController)
        }
    }
}