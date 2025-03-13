package com.example.wishlistapp

sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object AddScrreen: Screen("add_screen")
}