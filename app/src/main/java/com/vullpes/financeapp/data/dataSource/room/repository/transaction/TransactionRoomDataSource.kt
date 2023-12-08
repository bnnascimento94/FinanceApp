package com.vullpes.financeapp.data.dataSource.room.repository.transaction

import androidx.paging.PagingSource
import com.vullpes.financeapp.data.dataSource.room.entities.AccountTransaction
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface TransactionRoomDataSource {

    suspend fun createTransaction(transaction: Transaction)
    fun listTransactions(accountID:Int,date: Date): Flow<List<Transaction>>

    fun listAllTransactionsByAccountName(transactionName:String): Flow<List<Transaction>>
    fun listAllTransactionsByAccount(accountID:Int): Flow<List<Transaction>>

    fun getLastTransactionsByAccount(accountID:Int): Flow<List<Transaction>>
    suspend fun deleteTransaction(transactionID: Int)
    suspend fun updateTransaction(transaction: Transaction)

}