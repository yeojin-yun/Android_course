package com.example.musicapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(
    navController: NavHostController,
    paddingValue: PaddingValues
) {
    NavHost(navController = navController, startDestination = Screen.DrawerScreen.Accout.route, modifier = Modifier.padding(paddingValue)) {

    }
}