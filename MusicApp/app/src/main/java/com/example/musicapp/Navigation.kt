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
    NavHost(navController = navController, startDestination = Screen.DrawerScreen.Account.route, modifier = Modifier.padding(paddingValue)) {

        composable(Screen.BottomScreen.Home.bRoute) {
            HomeView()
        }
        composable(Screen.BottomScreen.Browse.bRoute) {
            BrowseView()
        }
        composable(Screen.BottomScreen.Library.bRoute) {
            LibraryView()
        }
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route) {
            SubscriptionView()
        }
    }
}