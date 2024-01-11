package com.vullpes.category



data class UiStateCategory(
    val loading:Boolean = false,
    val buttonCategoryEnabled:Boolean = false,
    val openModalCategory: Boolean = false,
    val categorySelected: Category? = null,
    val categories: List<Category> = emptyList()
)