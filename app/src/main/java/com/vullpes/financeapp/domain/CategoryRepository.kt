package com.vullpes.financeapp.domain

import com.vullpes.financeapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun createCategory(category: Category)
    fun listCategories(): Flow<List<Category>>
    suspend fun updateCategory(category: Category)
}