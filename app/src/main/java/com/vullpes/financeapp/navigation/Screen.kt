package com.vullpes.financeapp.navigation

import com.vullpes.financeapp.navigation.Constants.ACCOUNTID

sealed class Screen(val route:String) {
    object Login : Screen("login_screen")

    object Register : Screen("register_screen")
    object Home : Screen("home_screen")
    object Chart : Screen("chart_screen/$ACCOUNTID") {
        fun passAccountId(accountID: Int) = "chart_screen/$accountID"
    }
    object Profile : Screen("profile_screen")
}