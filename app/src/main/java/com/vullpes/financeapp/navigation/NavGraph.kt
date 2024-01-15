package com.vullpes.financeapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.vullpes.category.categoryRoute
import com.vullpes.charts.chartRoute
import com.vullpes.home.homeRoute
import com.vullpes.login.loginRoute
import com.vullpes.profile.profileRoute
import com.vullpes.transaction.transactionsRoute
import com.vullpes.uiregister.registerRoute
import com.vullpes.common.presentation.MessageDialog
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SetupNavGraph(
    firstDestination: String,
    navController: NavHostController
) {

    val openDialog = remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    if(openDialog.value){
        com.vullpes.common.presentation.MessageDialog(
            title = "Timeout",
            message = "It's been a while since the last activity, please login again",
            onDismiss = { /*TODO*/ },
            onNegativeClick = { /*TODO*/ }
        ) {
            openDialog.value = false
            com.vullpes.common.domain.session.UserSession.stopSession()
            navController.popBackStack()
            navController.navigate(com.vullpes.common.navigation.Screen.Login.route)
        }
    }
    NavHost(
        startDestination = firstDestination,
        navController = navController
    ) {


        loginRoute(
            onSignIn = {
                navController.popBackStack()
                navController.navigate(com.vullpes.common.navigation.Screen.Home.route)
            },
            onRegisterUser = {
                navController.navigate(com.vullpes.common.navigation.Screen.Register.route)
            }

        )
        registerRoute(onBackPressed = {
            navController.popBackStack()
        },
            onSignIn = {
                navController.popBackStack()
                navController.navigate(com.vullpes.common.navigation.Screen.Home.route)
            })
        chartRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onInteraction = {
                scope.launch {
                    com.vullpes.common.domain.session.UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )
        homeRoute(
            onExitAppClicked = {
                com.vullpes.common.domain.session.UserSession.stopSession()
                navController.popBackStack()
                navController.navigate(com.vullpes.common.navigation.Screen.Login.route)
            },
            onProfileClick = {
                navController.navigate(com.vullpes.common.navigation.Screen.Profile.route)
            },
            onChart = {
                navController.navigate(com.vullpes.common.navigation.Screen.Chart.passAccountId(it))
            },
            onCategory = {
                navController.navigate(com.vullpes.common.navigation.Screen.Category.route)
            },
            onTransactions = {
                navController.navigate(com.vullpes.common.navigation.Screen.Transactions.passAccountId(it))
            },
            onInteraction = {
                scope.launch {
                    com.vullpes.common.domain.session.UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )

        profileRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onInteraction = {
                scope.launch {
                    com.vullpes.common.domain.session.UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )

        categoryRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onInteraction = {
                scope.launch {
                    com.vullpes.common.domain.session.UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )

        transactionsRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onInteraction = {
                scope.launch {
                    com.vullpes.common.domain.session.UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )

    }
}













