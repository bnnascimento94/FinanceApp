package com.vullpes.financeapp.domain

import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRepository {

    suspend fun createTransaction(transaction: Transaction, account: Account, accountTo:Account?, transactionTransference:Transaction?)
    fun listTransactions(accountID: Int,date: Date): Flow<List<Transaction>>
    suspend fun deleteTransaction(transactionID: Int)
    suspend fun updateTransaction(transaction: Transaction)

    suspend fun findTransactionById(transactionID: Int): Transaction?
    fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
    fun getAllTransactionsByAccount(accountID: Int): Flow<List<Transaction>>
    fun listAllTransactionsByAccountName(transactionName:String): Flow<List<Transaction>>



}