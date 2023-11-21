package com.vullpes.financeapp.data

import com.vullpes.financeapp.domain.CategoryRepository
import com.vullpes.financeapp.domain.model.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl: CategoryRepository {
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