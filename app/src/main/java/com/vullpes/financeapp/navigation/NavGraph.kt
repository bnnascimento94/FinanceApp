package com.vullpes.financeapp.navigation

import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vullpes.financeapp.R
import com.vullpes.financeapp.domain.util.KindOfTransaction
import com.vullpes.financeapp.domain.util.UserSession
import com.vullpes.financeapp.navigation.Constants.ACCOUNTID
import com.vullpes.financeapp.presentation.authentication.login.LoginScreen
import com.vullpes.financeapp.presentation.authentication.login.LoginViewModel
import com.vullpes.financeapp.presentation.authentication.register.RegisterScreen
import com.vullpes.financeapp.presentation.authentication.register.RegisterViewModel
import com.vullpes.financeapp.presentation.category.CategoryScreen
import com.vullpes.financeapp.presentation.category.CategoryViewmodel
import com.vullpes.financeapp.presentation.charts.ChartViewModel
import com.vullpes.financeapp.presentation.charts.ChartsScreen
import com.vullpes.financeapp.presentation.components.LoadingDialog
import com.vullpes.financeapp.presentation.components.ModalBottomSheetAccount
import com.vullpes.financeapp.presentation.components.ModalBottomSheetCategory
import com.vullpes.financeapp.presentation.components.ModalBottomSheetChangePicture
import com.vullpes.financeapp.presentation.components.ModalBottomSheetTransactions
import com.vullpes.financeapp.presentation.home.HomeScreen
import com.vullpes.financeapp.presentation.home.HomeViewModel
import com.vullpes.financeapp.presentation.profile.ProfileScreen
import com.vullpes.financeapp.presentation.profile.ProfileViewModel
import com.vullpes.financeapp.presentation.transactions.TransactionsListScreen
import com.vullpes.financeapp.presentation.transactions.TransactionsListViewModel
import com.vullpes.financeapp.presentation.util.MessageDialog
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
        MessageDialog(
            title = "Timeout",
            message = "It's been a while since the last activity, please login again",
            onDismiss = { /*TODO*/ },
            onNegativeClick = { /*TODO*/ }
        ) {
            openDialog.value = false
            UserSession.stopSession()
            navController.popBackStack()
            navController.navigate(Screen.Login.route)
        }
    }
    NavHost(
        startDestination = firstDestination,
        navController = navController
    ) {


        loginRoute(
            onSignIn = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            },
            onRegisterUser = {
                navController.navigate(Screen.Register.route)
            }

        )
        registerRoute(onBackPressed = {
            navController.popBackStack()
        },
            onSignIn = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            })
        chartRoute(
            onBackPressed = {
                navController.popBackStack()
            },
            onInteraction = {
                scope.launch {
                    UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )
        homeRoute(
            onExitAppClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            },
            onProfileClick = {
                navController.navigate(Screen.Profile.route)
            },
            onChart = {
                navController.navigate(Screen.Chart.passAccountId(it))
            },
            onCategory = {
                navController.navigate(Screen.Category.route)
            },
            onTransactions = {
                navController.navigate(Screen.Transactions.passAccountId(it))
            },
            onInteraction = {
                scope.launch {
                    UserSession.startSession(onLimitReached = {
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
                    UserSession.startSession(onLimitReached = {
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
                    UserSession.startSession(onLimitReached = {
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
                    UserSession.startSession(onLimitReached = {
                        openDialog.value = true
                    })
                }
            }
        )

    }
}


fun NavGraphBuilder.loginRoute(
    onSignIn: () -> Unit,
    onRegisterUser:() -> Unit
) {

    composable(route = Screen.Login.route) {
        val viewModel: LoginViewModel = hiltViewModel()
        val context = LocalContext.current

        LoginScreen(
            uiStateLogin = viewModel.uiState,
            onUsernameChanged = {
                viewModel.setUsername(it)
            },
            onPasswordChanged = {
                viewModel.setPassword(it)
            },
            onSignInClicked = {
                viewModel.save(
                    onSuccess = {
                        onSignIn()
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onForgotPassword = {
                viewModel.forgotPassword(
                    onSuccess = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    },
                    onError = {error ->
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onRegisterUser = onRegisterUser,
            useBiometrics = {
                viewModel.biometricAuth(
                    onSuccess = {
                        onSignIn()
                    },
                    onError = {
                        Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
                    })
            }
        )
    }
}

fun NavGraphBuilder.registerRoute(
    onBackPressed: () -> Unit,
    onSignIn: () -> Unit
) {
    composable(route = Screen.Register.route) {
        val viewModel: RegisterViewModel = hiltViewModel()
        val context = LocalContext.current

        RegisterScreen(
            uiState = viewModel.uiState,
            onBackPressed = onBackPressed,
            onUsernameChanged = {
                viewModel.setUsername(it)
            },
            onEmailChanged = {
                viewModel.email(it)
            },
            onPasswordChanged = {
                viewModel.setPassword(it)
            },
            onConfirmPassword = {
                viewModel.confirmPassword(it)
            },
            onRegisterClicked = {
                viewModel.save(
                    onSuccess = {
                        onSignIn()
                    },
                    onError = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        )
    }
}

fun NavGraphBuilder.chartRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
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
            uiState = viewModel.uiState,
            onBackPressed = {
                onBackPressed()
                onInteraction()
            },
            onSelectedDates = { startDate, endDate ->
                viewModel.selectDates(startDate, endDate)
                onInteraction()
            }
        )
    }
}

fun NavGraphBuilder.transactionsRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(
        route = Screen.Transactions.route,
        arguments = listOf(navArgument(name = ACCOUNTID) {
            type = NavType.IntType
            nullable = false
            defaultValue = 0
        }
        )
    ) {
        val viewModel: TransactionsListViewModel = hiltViewModel()

        TransactionsListScreen(
            uiState = viewModel.uiState,
            onBackScreen = onBackPressed,
            onSearchItem = {
                viewModel.onSearchItem()
                onInteraction()
            },
            onActiveSearchChange = {
                viewModel.onChangeStatus(it)
                onInteraction()
            },
            onSearchTextChange = {
                viewModel.itemPesquisa(it)
                onInteraction()
            },
            onSelectedDate = {
                viewModel.getSearchByDate(it)
                onInteraction()
            }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.categoryRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = Screen.Category.route) {
        val viewModel: CategoryViewmodel = hiltViewModel()

        CategoryScreen(
            uiStateCategory =viewModel.uiState,
            onBackPressed = onBackPressed,
            openCategoryModal ={
                onInteraction()
                viewModel.openModalCategory(it)
            }
        )

        if (viewModel.uiState.openModalCategory) {
            ModalBottomSheetCategory(
                buttonSaveEnabled = viewModel.uiState.buttonCategoryEnabled,
                category = viewModel.uiState.categorySelected!!,
                onChangeCategoryName = {
                    viewModel.nameCategory(it)
                    onInteraction()
                },
                onChangeCategoryStatus = {
                    viewModel.statusCategory(it)
                    onInteraction()
                },
                onSave = {
                    viewModel.onSaveCategory()
                    onInteraction()
                },
                onDismiss = {
                    viewModel.closeModalCategory()
                    onInteraction()
                }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeRoute(
    onProfileClick: () -> Unit,
    onExitAppClicked: () -> Unit,
    onChart: (Int) -> Unit,
    onCategory: () -> Unit,
    onTransactions: (Int) -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = Screen.Home.route) {
        val viewModel: HomeViewModel = hiltViewModel()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        HomeScreen(
            uiState = viewModel.uiState,
            drawerState = drawerState,
            onProfileClick = onProfileClick,
            onMenuClick = {
                onInteraction()
                scope.launch {
                    drawerState.open()
                }
            },
            onCreateAccount = {
                onInteraction()
                viewModel.onOpenAccountModal()
            },
            onEditAccount = {
                onInteraction()
                viewModel.onOpenAccountModal(it)
            },
            onAccountSelected = { accountID ->
                onInteraction()
                viewModel.getAccountSelected(accountID)
            },
            onDeposit = { accountID ->
                onInteraction()
                viewModel.onOpenModalTransacton(
                    accountID,
                    KindOfTransaction.DEPOSIT
                )
            },
            onWithdraw = { accountID ->
                onInteraction()
                viewModel.onOpenModalTransacton(
                    accountID,
                    KindOfTransaction.WITHDRAW
                )
            },
            onTransference = { accountID ->
                onInteraction()
                viewModel.onOpenModalTransacton(
                    accountID,
                    KindOfTransaction.TRANSFERENCE
                )
            },
            onCategoryClicked = {
                onInteraction()
                onCategory()
            },
            onExitAppClicked = {
                onInteraction()
                viewModel.logoutUser(onSuccess = {
                    onExitAppClicked()
                })
            },
            onChart = {
                onInteraction()
                onChart(it)
            },
            allTransactions = { accountID ->
                onTransactions(accountID)
                onInteraction()
            }
        )

        if (viewModel.uiState.openTransactionModal) {
            ModalBottomSheetTransactions(
                buttonSaveEnabled = viewModel.uiState.buttonSaveTransactionEnabled,
                transaction = viewModel.uiState.transaction,
                inputValueTransaction = viewModel.uiState.valueTransaction,
                listCategory = viewModel.uiState.categories.filter { it.active },
                listAccounts = viewModel.uiState.accounts.filter { it.activeAccount },
                onKindOfTransactionSelected = {
                    viewModel.onTransaction(it)
                    onInteraction()
                },
                onTransactionNameChanged = {
                    viewModel.onTransactionName(it)
                    onInteraction()
                },
                onCategorySelected = {
                    viewModel.onTransactionCategory(it)
                    onInteraction()
                },
                onAccountSelected = {
                    viewModel.onTransactionAccountTo(it)
                    onInteraction()
                },
                onValueTransaction = {
                    viewModel.onValueSelected(it)
                    onInteraction()
                },
                onDismiss = {
                    viewModel.onCloseModalTransaction()
                    onInteraction()
                },
                onSave = {
                    viewModel.onSave()
                    onInteraction()
                },
                accountSelected = viewModel.uiState.accountSelected!!,
                withdrawalBlocked = viewModel.uiState.withdrawalBlocked
            )
        }

        if (viewModel.uiState.openAccountModal) {
            ModalBottomSheetAccount(
                activateSaveAccount = viewModel.uiState.buttonSaveAccountEnabled,
                inputValueAccount = viewModel.uiState.valueAccount,
                account = viewModel.uiState.accountCreateUpdate,
                onAccountSaveBlocked = viewModel.uiState.accountNameInvalid,
                onChangeAccountStatus = { status ->
                    viewModel.statusAccountChanged(status)
                    onInteraction()
                },
                onChangeAccountName = { accountName ->
                    viewModel.statusAccountNameChanged(
                        accountName
                    )
                    onInteraction()
                },
                onChangeAccountValue = { accountValue ->
                    viewModel.statusAccountValueChanged(
                        accountValue
                    )
                    onInteraction()
                },
                onSave = {
                    viewModel.onSaveAccount()
                    onInteraction()
                },
                onDismiss = {
                    viewModel.closeAccountModal()
                    onInteraction()
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.profileRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = Screen.Profile.route) {
        val viewModel: ProfileViewModel = hiltViewModel()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        ProfileScreen(
            uiStateProfile = viewModel.uiState,
            onBackScreen = onBackPressed,
            onEditImage = {
                viewModel.dialogImageOpen()
                onInteraction()
            },
            onSave = {
                viewModel.onSave(onSuccess = {
                    Toast.makeText(context,
                        context.getString(R.string.user_updated), Toast.LENGTH_SHORT).show()                         
                }, onError = {message -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show()})
                onInteraction()
            },
            onNameChanged = {
                viewModel.setName(it)
                onInteraction()
            },
            onEmailChanged = {
                viewModel.setEmail(it)
                onInteraction()
            },
            onPasswordChanged = {
                viewModel.setPassword(it)
                onInteraction()
            },
            onChangeBiometricAuth = {
                viewModel.biometricAllowance(it)
            }
        )

        if (viewModel.uiState.openImageDialog) {
            ModalBottomSheetChangePicture(
                onDismiss = {
                    viewModel.dialogImageOpen()
                    onInteraction()
                },
                onAddImageFromGallery = { sheetStatus,uri ->
                    scope.launch {
                        sheetStatus.hide()
                    }
                    viewModel.setImageBitmap(uri)
                    onInteraction()
                },
                onAddImage = {
                    scope.launch {
                        it.hide()
                    }
                    viewModel.setImageBitmap()
                    onInteraction()
                },
                createUri = {
                    viewModel.createImageUri()
                }
            )
        }

        if(viewModel.uiState.loading){
            LoadingDialog {}
        }

    }
}