package com.vullpes.category


import com.vullpes.room.repository.category.CategoryRoomDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(private val categoryRoomDataSource: CategoryRoomDataSource) :
    CategoryRepository {
    override suspend fun createCategory(category: Category) {
        categoryRoomDataSource.createCategory(category)
    }

    override fun listCategories(): Flow<List<Category>> {
        return categoryRoomDataSource.listCategories()
    }

    override suspend fun categoriesSaved(): List<Category> {
        return categoryRoomDataSource.listCategoriesSaved()
    }

    override suspend fun updateCategory(category: Category) {
        categoryRoomDataSource.updateCategory(category)
    }
}