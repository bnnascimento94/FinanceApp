package com.vullpes.room.repository.transaction

import com.vullpes.account.Account
import com.vullpes.account.DayBalance
import com.vullpes.transaction.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRoomDataSource {


    fun listTransactions(accountID: Int, date: Date): Flow<List<Transaction>>

    fun listAllTransactionsByAccountName(transactionName: String, accountID:Int): Flow<List<Transaction>>
    fun listAllTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
    suspend fun listTransactionsByAccountAndDate(accountID: Int, date1: Date, date2:Date): List<Transaction>
    fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
    suspend fun findTransactionById(transactionId: Int): Transaction?
    suspend fun createTransaction(
        transaction: Transaction,
        account: Account,
        accountTo: Account?,
        transactionTransference: Transaction?,
        dayBalance: DayBalance,
        dayBalanceAccountTo: DayBalance?
    )

}