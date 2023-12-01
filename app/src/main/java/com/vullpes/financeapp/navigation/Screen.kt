package com.vullpes.financeapp.navigation

sealed class Screen(val route:String) {
    object Login : Screen("login_screen")

    object Register : Screen("register_screen")
    object Home : Screen("home_screen")
    object Chart : Screen("chart_screen") {
        fun passAccountId(accountID: Int) = "chart_screen/$accountID"
    }
    object Profile : Screen("profile_screen")
}