package com.example.musicapp

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
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

//Drawer의 아이템으로 쓰기 위해 배열로 선언
val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)