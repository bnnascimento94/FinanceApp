package com.vullpes.financeapp.data.dataSource.room.repository.category

import com.vullpes.financeapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRoomDataSource {
    suspend fun createCategory(category: Category)
    fun listCategories(): Flow<List<Category>>
    suspend fun updateCategory(category: Category)
}