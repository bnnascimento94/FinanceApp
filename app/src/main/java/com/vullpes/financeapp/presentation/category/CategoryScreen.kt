package com.vullpes.financeapp.presentation.category

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vullpes.financeapp.domain.model.Category
import com.vullpes.financeapp.presentation.category.components.CategoryItem
import com.vullpes.financeapp.presentation.category.components.CategoryTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    uiStateCategory: UiStateCategory,
    onBackPressed:() -> Unit,
    openCategoryModal:(Category?) -> Unit,
){
    Scaffold(
        topBar = {
            CategoryTopBar(onBackPressed = onBackPressed, openCategoryModal = {openCategoryModal(null)})
        }
    ) {  paddingValues ->
        
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues)){
            items(uiStateCategory.categories){category ->
                CategoryItem(category = category, onCategory = {openCategoryModal(category)})
            }
        }


    }
}