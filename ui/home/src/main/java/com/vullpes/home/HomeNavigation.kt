package com.vullpes.home

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vullpes.common.domain.KindOfTransaction
import com.vullpes.common.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeRoute(
    onProfileClick: () -> Unit,
    onExitAppClicked: () -> Unit,
    onChart: (Int) -> Unit,
    onCategory: () -> Unit,
    onTransactions: (Int) -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = com.vullpes.common.navigation.Screen.Home.route) {
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
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT
                )
            },
            onWithdraw = { accountID ->
                onInteraction()
                viewModel.onOpenModalTransacton(
                    accountID,
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW
                )
            },
            onTransference = { accountID ->
                onInteraction()
                viewModel.onOpenModalTransacton(
                    accountID,
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE
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
            com.vullpes.components.ModalBottomSheetTransactions(
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
            com.vullpes.components.ModalBottomSheetAccount(
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