package com.vullpes.room.repository.category

import com.vullpes.category.Category
import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.entities.toCategory
import com.vullpes.room.entities.toCategoryDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase):
    CategoryRoomDataSource {
    override suspend fun createCategory(category: Category) {
        try {
           val categoryDao =  financeAppDatabase.categoryDao()
            categoryDao.insert(category.toCategoryDb())
        }catch (e:Exception){
            throw e
        }
    }

    override fun listCategories(): Flow<List<Category>> {
        try {
            val categoryDao =  financeAppDatabase.categoryDao()
            return categoryDao.getCategories().map {listaDeCategorias ->
                listaDeCategorias.map { it.toCategory() }
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun listCategoriesSaved(): List<Category> {
        try {
            val categoryDao =  financeAppDatabase.categoryDao()
            return categoryDao.getCategoriesSaved().map { it.toCategory() }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun updateCategory(category: Category) {
        try {
            val categoryDao =  financeAppDatabase.categoryDao()
            categoryDao.update(category.toCategoryDb())
        }catch (e:Exception){
            throw e
        }
    }
}