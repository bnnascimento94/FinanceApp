package com.vullpes.financeapp.data.dataSource.room.repository.category

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.toCategory
import com.vullpes.financeapp.data.dataSource.room.entities.toCategoryDb
import com.vullpes.financeapp.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase): CategoryRoomDataSource {
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