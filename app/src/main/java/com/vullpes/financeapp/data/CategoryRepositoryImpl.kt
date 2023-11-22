package com.vullpes.financeapp.data

import com.vullpes.financeapp.data.dataSource.room.repository.category.CategoryRoomDataSource
import com.vullpes.financeapp.domain.CategoryRepository
import com.vullpes.financeapp.domain.model.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(categoryRoomDataSource: CategoryRoomDataSource) : CategoryRepository {
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