package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(accountDb: AccountDb)

    @Update
    fun update(accountDb: AccountDb)

    @Delete
    fun delete(accountDb: AccountDb)

    @Query("select * from accountdb")
    fun getAccountDb(): Flow<List<AccountDb>>


}