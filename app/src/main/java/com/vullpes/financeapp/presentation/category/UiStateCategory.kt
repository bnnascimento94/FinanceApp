package com.vullpes.financeapp.presentation.category

import com.vullpes.financeapp.domain.model.Category

data class UiStateCategory(
    val loading:Boolean = false,
    val buttonCategoryEnabled:Boolean = false,
    val openModalCategory: Boolean = false,
    val categorySelected: Category? = null,
    val categories: List<Category> = emptyList()
)