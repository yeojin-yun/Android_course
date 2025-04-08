package com.example.musicapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    navController: NavHostController,
    viewModel: MainViewModel,
    paddingValue: PaddingValues
) {
    NavHost(navController = navController, startDestination = Screen.DrawerScreen.AddAccount.route, modifier = Modifier.padding(paddingValue)) {
        composable(Screen.DrawerScreen.AddAccount.route) {}
        composable(Screen.DrawerScreen.Subscription.route) {}
    }
}