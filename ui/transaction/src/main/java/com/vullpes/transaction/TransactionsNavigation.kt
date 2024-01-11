package com.vullpes.transaction

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.transactionsRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(
        route = com.vullpes.util.navigation.Screen.Transactions.route,
        arguments = listOf(navArgument(name = com.vullpes.util.navigation.Constants.ACCOUNTID) {
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
