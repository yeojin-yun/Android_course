package com.example.musicapp

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
    sealed class BottomScreen(
        val bTitle: String,
        val bRoute: String,
        @DrawableRes val icon: Int
    ): Screen(title = bTitle, route = bRoute) {
        object Home: BottomScreen(
            bTitle = "Home",
            bRoute = "homescreen",
            icon = R.drawable.baseline_music_note_24,
        )
        object Library: BottomScreen(
            bTitle = "Library",
            bRoute = "libraryscreen",
            icon = R.drawable.baseline_video_library_24,
        )
        object Browse: BottomScreen(
            bTitle = "Browse",
            bRoute = "browsescreen",
            icon = R.drawable.baseline_apps_24,
        )
    }



    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) : Screen(title = dTitle, route = dRoute) {
        object Account: DrawerScreen(
            dTitle = "Account",
            dRoute = "account",
            icon = R.drawable.baseline_account_circle_24
        )
        object Subscription: DrawerScreen(
            dTitle = "Subscription",
            dRoute = "subscription",
            icon = R.drawable.baseline_monetization_on_24
        )
        object AddAccount: DrawerScreen(
            dTitle = "Add Account",
            dRoute = "add_account",
            icon = R.drawable.baseline_person_add_alt_1_24
        )
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.Home,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library,
)

//Drawer의 아이템으로 쓰기 위해 배열로 선언
val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)