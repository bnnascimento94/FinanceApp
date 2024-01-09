package com.vullpes.category

import com.vullpes.financeapp.category.domain.Category

data class UiStateCategory(
    val loading:Boolean = false,
    val buttonCategoryEnabled:Boolean = false,
    val openModalCategory: Boolean = false,
    val categorySelected: Category? = null,
    val categories: List<Category> = emptyList()
)