package com.vullpes.financeapp.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vullpes.financeapp.presentation.authentication.login.LoginScreen
import com.vullpes.financeapp.presentation.authentication.login.LoginViewModel
import com.vullpes.financeapp.presentation.authentication.register.RegisterScreen
import com.vullpes.financeapp.presentation.authentication.register.RegisterViewModel
import com.vullpes.financeapp.presentation.charts.ChartsScreen
import com.vullpes.financeapp.presentation.home.HomeScreen
import com.vullpes.financeapp.presentation.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(
    firstDestination: String,
    navController: NavHostController,
    onDataLoaded: () -> Unit
) {

    NavHost(
        startDestination = firstDestination,
        navController = navController
    ) {

        loginRoute()
        registerRoute(onBackPressed = {})
        chartRoute(onBackPressed = {})
        homeRoute(onExitAppClicked = {}, onProfileClick = {})

    }
}


fun NavGraphBuilder.loginRoute() {
    composable(route = Screen.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()

        LoginScreen(
            uiStateLogin = viewModel.uiState,
            onUsernameChanged = {},
            onPasswordChanged = {},
            onSignInClicked = {},
            onForgotPassword = {}
        )
    }
}

fun NavGraphBuilder.registerRoute(
    onBackPressed: () -> Unit,
) {
    composable(route = Screen.Register.route) {
        val viewModel: RegisterViewModel = hiltViewModel()

        RegisterScreen(
            uiState = viewModel.uiState,
            onBackPressed = onBackPressed,
            onUsernameChanged = {},
            onPasswordChanged = {},
            onConfirmPassword = {},
            onRegisterClicked = {}
        )
    }
}

fun NavGraphBuilder.chartRoute(
    onBackPressed: () -> Unit
) {
    composable(route = Screen.Chart.route) {
        val viewModel: RegisterViewModel = hiltViewModel()

        ChartsScreen(
            onBackPressed = onBackPressed
        )
    }
}

fun NavGraphBuilder.homeRoute(
    onProfileClick: () -> Unit,
    onExitAppClicked: () -> Unit
) {
    composable(route = Screen.Login.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        HomeScreen(
            uiState = viewModel.uiState,
            drawerState = drawerState,
            onProfileClick = onProfileClick,
            onMenuClick = {
                scope.launch {
                    drawerState.open()
                }
            },
            onCreateAccount = {},
            onAccountSelected = { accountID ->

            },
            onDeposit = {accountID ->},
            onWithdraw = {accountID ->},
            onTransference = {accountID ->},
            onCategoryClicked = {},
            onExitAppClicked = onExitAppClicked,
            onChart = {}
        )
    }
}