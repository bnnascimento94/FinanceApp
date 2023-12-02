package com.vullpes.financeapp.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vullpes.financeapp.navigation.Constants.ACCOUNTID
import com.vullpes.financeapp.presentation.authentication.login.LoginScreen
import com.vullpes.financeapp.presentation.authentication.login.LoginViewModel
import com.vullpes.financeapp.presentation.authentication.register.RegisterScreen
import com.vullpes.financeapp.presentation.authentication.register.RegisterViewModel
import com.vullpes.financeapp.presentation.charts.ChartViewModel
import com.vullpes.financeapp.presentation.charts.ChartsScreen
import com.vullpes.financeapp.presentation.home.HomeScreen
import com.vullpes.financeapp.presentation.home.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(
    firstDestination: String,
    navController: NavHostController
) {

    NavHost(
        startDestination = firstDestination,
        navController = navController
    ) {

        loginRoute(onSignIn = {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        })
        registerRoute(onBackPressed = {
            navController.popBackStack()
        })
        chartRoute(onBackPressed = {
            navController.popBackStack()
        })
        homeRoute(onExitAppClicked = {
            navController.popBackStack()
            navController.navigate(Screen.Login.route)
        },
        onProfileClick = {
            navController.navigate(Screen.Login.route)
        },
        onChart = {
            navController.navigate(Screen.Chart.route)
        })

    }
}


fun NavGraphBuilder.loginRoute(
    onSignIn:()-> Unit
) {
    composable(route = Screen.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()

        LoginScreen(
            uiStateLogin = viewModel.uiState,
            onUsernameChanged = {},
            onPasswordChanged = {},
            onSignInClicked = {
               onSignIn()
            },
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
    composable(route = Screen.Chart.route,
        arguments = listOf(navArgument(name = ACCOUNTID) {
            type = NavType.IntType
            nullable = false
            defaultValue = 0
        }
        )
    ) {
        val viewModel: ChartViewModel = hiltViewModel()

        ChartsScreen(
            uiState =viewModel.uiState,
            onBackPressed = onBackPressed
        )
    }
}

fun NavGraphBuilder.homeRoute(
    onProfileClick: () -> Unit,
    onExitAppClicked: () -> Unit,
    onChart:(Int) -> Unit
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
            onAccountSelected = { accountID -> },
            onDeposit = {accountID ->},
            onWithdraw = {accountID ->},
            onTransference = {accountID ->},
            onCategoryClicked = {},
            onExitAppClicked = onExitAppClicked,
            onChart = onChart
        )
    }
}