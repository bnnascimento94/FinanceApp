package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryDb: CategoryDb)

    @Update
    fun update(categoryDb: CategoryDb)

    @Delete
    fun delete(categoryDb: CategoryDb)

    @Query("select * from categorydb")
    fun getCategories(): Flow<List<CategoryDb>>

}