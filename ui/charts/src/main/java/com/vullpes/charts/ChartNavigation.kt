package com.vullpes.charts

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.chartRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = com.vullpes.util.navigation.Screen.Chart.route,
        arguments = listOf(navArgument(name = com.vullpes.util.navigation.Constants.ACCOUNTID) {
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