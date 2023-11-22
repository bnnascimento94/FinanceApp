package com.vullpes.financeapp.data.dataSource.room.repository.category

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase): CategoryRoomDataSource {
    override suspend fun createCategory(category: Category) {
        TODO("Not yet implemented")
    }

    override fun listCategories(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateCategory(category: Category) {
        TODO("Not yet implemented")
    }
}