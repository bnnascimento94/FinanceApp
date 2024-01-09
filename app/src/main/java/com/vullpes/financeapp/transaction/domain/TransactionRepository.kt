package com.vullpes.financeapp.transaction.domain

import com.vullpes.financeapp.account.domain.Account
import com.vullpes.financeapp.charts.domain.DayBalance
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRepository {

    suspend fun createTransaction(
        transaction: Transaction,
        account: Account,
        accountTo: Account?,
        transactionTransference: Transaction?,
        dayBalance: DayBalance,
        dayBalanceAccountTo: DayBalance?
    )

    fun listTransactions(accountID: Int, date: Date): Flow<List<Transaction>>
    suspend fun findTransactionById(transactionID: Int): Transaction?
    fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
    fun getAllTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
    fun listAllTransactionsByAccountName(transactionName: String, accountID: Int): Flow<List<Transaction>>
    suspend fun listTransactionsByAccountAndDate(accountID: Int, date1: Date, date2:Date): List<Transaction>


}