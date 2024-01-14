package com.vullpes.room.repository.transaction

import com.vullpes.account.Account
import com.vullpes.account.DayBalance

import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.entities.toAccountDb
import com.vullpes.room.entities.toDayBalanceDb
import com.vullpes.room.entities.toTransaction
import com.vullpes.room.entities.toTransactionDb
import com.vullpes.transaction.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject


class TransactionRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase):
    TransactionRoomDataSource {


    override suspend fun createTransaction(
        transaction: Transaction,
        account: Account,
        accountTo: Account?,
        transactionTransference: Transaction?,
        dayBalance: DayBalance,
        dayBalanceAccountTo: DayBalance?
    ) {
        try {
            financeAppDatabase.runInTransaction{
                val transactionDao = financeAppDatabase.transactionDao()
                val accountDao = financeAppDatabase.accountDao()
                val dayBalanceDao = financeAppDatabase.dayBalanceDao()
                val transactionDb = transaction.toTransactionDb()

                transactionDao.insert(transactionDb)
                accountDao.update(account.toAccountDb())
                dayBalanceDao.insert(dayBalance.toDayBalanceDb())
                if(transaction.transference){
                    accountTo?.let {
                        transactionTransference?.let {
                            transactionDao.insert(it.toTransactionDb())
                        }
                        accountDao.update(accountTo.toAccountDb())
                        dayBalanceAccountTo?.let {
                            dayBalanceDao.insert(it.toDayBalanceDb())
                        }
                    }
                }
            }
        }catch (e:Exception){
            throw e
        }

    }


    override fun listTransactions(accountID: Int, date: Date): Flow<List<Transaction>> {
        return try {
            val transactionDao = financeAppDatabase.transactionDao()
            val localDate = Instant.ofEpochMilli(date.time)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            val date1 = Date.from(localDate.minusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
            val date2 = Date.from(localDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())
            transactionDao.getTransactionByID(accountID = accountID, data1 = date1 , data2 = date2 ).map { listAccountTransaction ->
                listAccountTransaction.map { it.toTransaction() }
            }
        }catch (e:Exception){
            throw e
        }
    }

    override fun listAllTransactionsByAccountName(transactionName: String, accountID: Int): Flow<List<Transaction>> {
        return try {
            val transactionDao = financeAppDatabase.transactionDao()
            transactionDao.getAllTransactionsByNameAndAccount(transactionName, accountID).map { listAccountTransaction ->
                listAccountTransaction.map { it.toTransaction() }
            }
        }catch (e:Exception){
            throw e
        }
    }

    override fun listAllTransactionsByAccount(accountID: Int): Flow<List<Transaction>> {
        return try {
            val transactionDao = financeAppDatabase.transactionDao()
            transactionDao.getAllTransactionsByAccount(accountID = accountID).map { listAccountTransaction ->
                listAccountTransaction.map { it.toTransaction() }
            }
        }catch (e:Exception){
            throw e
        }
    }

    override suspend fun listTransactionsByAccountAndDate(
        accountID: Int,
        date1: Date,
        date2: Date
    ): List<Transaction> {
        val transactionDao = financeAppDatabase.transactionDao()
        return transactionDao.listTransactionsByAccountAndDate(accountID, date1, date2).map {
                listAccountTransaction -> listAccountTransaction.toTransaction()
        }
    }

    override fun getLastTransactionsByAccount(accountID: Int): Flow<List<Transaction>> {
        return try {
            val transactionDao = financeAppDatabase.transactionDao()
            transactionDao.getLastTransactionsByAccount(accountID = accountID).map { listAccountTransaction ->
                listAccountTransaction.map { it.toTransaction() }
            }
        }catch (e:Exception){
            throw e
        }
    }




    override suspend fun findTransactionById(transactionId: Int): Transaction? {
        val transactionDao = financeAppDatabase.transactionDao()
        return transactionDao.getTransactionByID(transactionID = transactionId)?.toTransaction()
    }



}