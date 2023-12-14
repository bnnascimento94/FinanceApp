package com.vullpes.financeapp.data.dataSource.room.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(accountDb: AccountDb): Long

    @Update
    fun update(accountDb: AccountDb)

    @Delete
    fun delete(accountDb: AccountDb)

    @Query("select * from accountdb")
    fun getAccountDb(): Flow<List<AccountDb>>

    @Query("select * from accountdb where accountID = :accountID")
    fun findAccountById(accountID: Int): AccountDb

    @Query("select * from accountdb " +
            "JOIN transactiondb on (accountdb.accountID = transactiondb.accountFromID or accountdb.accountID = transactiondb.accountToID )" +
            "where dateTransaction between :date1 and :date2")
    fun loadAccountTransactionsByDate(date1: Date, date2: Date): Flow<Map<AccountDb, List<TransactionDb>>>

    @Query("select * from accountdb " +
            "JOIN transactiondb on (accountdb.accountID = transactiondb.accountFromID or accountdb.accountID = transactiondb.accountToID ) " +
            "where accountdb.accountID = :accountID and dateTransaction between :date1 and :date2")
    fun loadAccountTransactionsByDateAndAccountID(accountID: Int,date1: Date, date2: Date): Flow<Map<AccountDb, List<TransactionDb>>>


}