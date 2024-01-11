package com.vullpes.category

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vullpes.components.ModalBottomSheetCategory

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.categoryRoute(
    onBackPressed: () -> Unit,
    onInteraction: () -> Unit
) {
    composable(route = com.vullpes.util.navigation.Screen.Category.route) {
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