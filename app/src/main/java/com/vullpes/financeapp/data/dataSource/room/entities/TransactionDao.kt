package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transactionDb: TransactionDb)

    @Update
    fun update(transactionDb: TransactionDb)

    @Delete
    fun delete(transactionDb: TransactionDb)

    @Transaction
    @Query("select * from transactiondb where accountID = :accountID and dateTransaction between :date1 and :date2")
    fun getTransactionsByAccountAndDate(accountID:Int,date1:Date, date2: Date): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where categoryID = :categoryID and dateTransaction between :date1 and :date2")
    fun getTransactionsByCategoryAndDate(categoryID:Int,date1:Date, date2: Date): Flow<List<AccountTransaction>>


}

