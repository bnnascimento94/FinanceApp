package com.vullpes.financeapp.navigation

import android.widget.Toast
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vullpes.financeapp.domain.util.KindOfTransaction
import com.vullpes.financeapp.navigation.Constants.ACCOUNTID
import com.vullpes.financeapp.presentation.authentication.login.LoginScreen
import com.vullpes.financeapp.presentation.authentication.login.LoginViewModel
import com.vullpes.financeapp.presentation.authentication.register.RegisterScreen
import com.vullpes.financeapp.presentation.authentication.register.RegisterViewModel
import com.vullpes.financeapp.presentation.category.CategoryScreen
import com.vullpes.financeapp.presentation.category.CategoryViewmodel
import com.vullpes.financeapp.presentation.charts.ChartViewModel
import com.vullpes.financeapp.presentation.charts.ChartsScreen
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
        },
            onSignIn = {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            })
        chartRoute(onBackPressed = {
            navController.popBackStack()
        })
        homeRoute(
            onExitAppClicked = {
                navController.popBackStack()
                navController.navigate(Screen.Login.route)
            },
            onProfileClick = {
                navController.navigate(Screen.Profile.route)
            },
            onChart = {
                navController.navigate(Screen.Chart.route)
            },
            onCategory = {
                navController.navigate(Screen.Category.route)
            },
            onTransactions = {
                navController.navigate(Screen.Transactions.passAccountId(it))
            }
        )

        profileRoute(
            onBackPressed = {
                navController.popBackStack()
            }
        )

        categoryRoute(
            onBackPressed = {
                navController.popBackStack()
            }
        )

        transactionsRoute(
            onBackPressed = {
                navController.popBackStack()
            }
        )

    }
}


fun NavGraphBuilder.loginRoute(
    onSignIn: () -> Unit
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
            onPasswordChanged = {
                viewModel.confirmPassword(it)
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
            uiState = viewModel.uiState,
            onBackPressed = onBackPressed
        )
    }
}

fun NavGraphBuilder.transactionsRoute(
    onBackPressed: () -> Unit
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
            onSearchItem = { viewModel.onSearchItem() },
            onActiveSearchChange = { viewModel.onChangeStatus(it) },
            onSearchTextChange = { viewModel.itemPesquisa(it)  },
            onSelectedDate = {viewModel.getSearchByDate(it)}
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.categoryRoute(
    onBackPressed: () -> Unit
) {
    composable(route = Screen.Category.route) {
        val viewModel: CategoryViewmodel = hiltViewModel()

        CategoryScreen(
            uiStateCategory =viewModel.uiState,
            onBackPressed = onBackPressed,
            openCategoryModal ={
                viewModel.openModalCategory(it)
            }
        )

        if (viewModel.uiState.openModalCategory) {
            ModalBottomSheetCategory(
                buttonSaveEnabled = viewModel.uiState.buttonCategoryEnabled,
                category = viewModel.uiState.categorySelected!!,
                onChangeCategoryName = {viewModel.nameCategory(it)},
                onChangeCategoryStatus = {viewModel.statusCategory(it)},
                onSave = {viewModel.onSaveCategory()},
                onDismiss = {viewModel.closeModalCategory()}
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
            onCreateAccount = {
                viewModel.onOpenAccountModal()
            },
            onEditAccount = {
                viewModel.onOpenAccountModal(it)
            },
            onAccountSelected = { accountID ->
                viewModel.getAccountSelected(accountID)
            },
            onDeposit = { accountID ->
                viewModel.onOpenModalTransacton(
                    accountID,
                    KindOfTransaction.DEPOSIT
                )
            },
            onWithdraw = { accountID ->
                viewModel.onOpenModalTransacton(
                    accountID,
                    KindOfTransaction.WITHDRAW
                )
            },
            onTransference = { accountID ->
                viewModel.onOpenModalTransacton(
                    accountID,
                    KindOfTransaction.TRANSFERENCE
                )
            },
            onCategoryClicked = onCategory,
            onExitAppClicked = {
                viewModel.logoutUser(onSuccess = {
                    onExitAppClicked()
                })
            },
            onChart = onChart,
            allTransactions = { accountID -> onTransactions(accountID)}
        )

        if (viewModel.uiState.openTransactionModal) {
            ModalBottomSheetTransactions(
                buttonSaveEnabled = viewModel.uiState.buttonSaveTransactionEnabled,
                transaction = viewModel.uiState.transaction,
                listCategory = viewModel.uiState.categories,
                listAccounts = viewModel.uiState.accounts,
                onKindOfTransactionSelected = { viewModel.onTransaction(it) },
                onTransactionNameChanged = { viewModel.onTransactionName(it) },
                onCategorySelected = { viewModel.onTransactionCategory(it) },
                onAccountSelected = { viewModel.onTransactionAccountTo(it) },
                onValueTransaction = { viewModel.onValueSelected(it) },
                onDismiss = {},
                onSave = {
                    viewModel.onSave()
                }
            )
        }

        if (viewModel.uiState.openAccountModal) {
            ModalBottomSheetAccount(
                activateSaveAccount = viewModel.uiState.buttonSaveAccountEnabled,
                account = viewModel.uiState.accountSelected!!,
                onChangeAccountStatus = { status -> viewModel.statusAccountChanged(status) },
                onChangeAccountName = { accountName ->
                    viewModel.statusAccountNameChanged(
                        accountName
                    )
                },
                onChangeAccountValue = { accountValue ->
                    viewModel.statusAccountValueChanged(
                        accountValue
                    )
                },
                onSave = { viewModel.onSaveAccount() },
                onDismiss = { viewModel.onCloseAccountModal() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.profileRoute(
    onBackPressed: () -> Unit
) {
    composable(route = Screen.Profile.route) {
        val viewModel: ProfileViewModel = hiltViewModel()
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        ProfileScreen(
            uiStateProfile = viewModel.uiState,
            onBackScreen = onBackPressed,
            onEditImage = { viewModel.dialogImageOpen() },
            onSave = { viewModel.onSave() },
            onNameChanged = { viewModel.setName(it) },
            onEmailChanged = { viewModel.setEmail(it) },
            onPasswordChanged = { viewModel.setPassword(it) }
        )

        if (viewModel.uiState.openImageDialog) {
            ModalBottomSheetChangePicture(
                onDismiss = {
                    scope.launch {
                        it.hide()
                    }
                },
                onAddImageFromGallery = {
                    viewModel.setImageBitmap(it)
                },
                onAddImage = {
                    viewModel.setImageBitmap()
                },
                createUri = { viewModel.createImageUri() }
            )
        }

    }
}