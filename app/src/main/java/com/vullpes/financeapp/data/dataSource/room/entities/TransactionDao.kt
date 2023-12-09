package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.paging.PagingSource
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
    @Query("select * from transactiondb where accountFromID = :accountID and dateTransaction between :date1 and :date2")
    fun getTransactionsByAccountAndDate(accountID:Int,date1:Date, date2: Date): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where accountFromID = :accountID  and dateTransaction between :data1 and :data2 order by dateTransaction desc ")
    fun getTransactionByID(accountID: Int, data1:Date, data2:Date): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where accountFromID = :accountID order by transactionID limit 20 ")
    fun getLastTransactionsByAccount(accountID: Int): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where accountFromID = :accountID ")
    fun getAllTransactionsByAccount(accountID: Int): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where name LIKE '%' || :nameAccount || '%' order by dateTransaction desc")
    fun getAllTransactionsByName(nameAccount: String): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where transactionID = :transactionID ")
    fun getTransactionByID(transactionID:Int): AccountTransaction?

    @Transaction
    @Query("select * from transactiondb where categoryID = :categoryID and dateTransaction between :date1 and :date2")
    fun getTransactionsByCategoryAndDate(categoryID:Int,date1:Date, date2: Date): Flow<List<AccountTransaction>>

    @Transaction
    @Query("select * from transactiondb where accountFromID = :accountID")
    fun getListTransactionsByAccount(accountID: Int): PagingSource<Int, AccountTransaction>



}

