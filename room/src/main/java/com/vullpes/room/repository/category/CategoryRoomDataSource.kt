package com.vullpes.room.repository.category

import com.vullpes.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRoomDataSource {
    suspend fun createCategory(category: Category)
    fun listCategories(): Flow<List<Category>>
    suspend fun listCategoriesSaved(): List<Category>
    suspend fun updateCategory(category: Category)
}