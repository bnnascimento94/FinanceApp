package com.vullpes.util.navigation

import com.vullpes.util.navigation.Constants.ACCOUNTID

sealed class Screen(val route:String) {
    object Login : Screen("login_screen")

    object Register : Screen("register_screen")
    object Home : Screen("home_screen")
    object Chart : Screen("chart_screen/{$ACCOUNTID}") {
        fun passAccountId(accountID: Int) = "chart_screen/$accountID"
    }
    object Profile : Screen("profile_screen")

    object Category : Screen("category_screen")

    object Transactions : Screen("transaction_screen/{$ACCOUNTID}") {
        fun passAccountId(accountID: Int) = "transaction_screen/$accountID"
    }
}