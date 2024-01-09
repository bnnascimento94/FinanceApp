package com.vullpes.financeapp.category.domain

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun createCategory(category: Category)
    fun listCategories(): Flow<List<Category>>
    suspend fun categoriesSaved(): List<Category>
    suspend fun updateCategory(category: Category)
}