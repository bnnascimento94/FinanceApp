package com.vullpes.room.repository.charts

import com.vullpes.room.FinanceAppDatabase
import com.vullpes.room.entities.TransactionDb
import com.vullpes.room.entities.toTransaction
import com.vullpes.transaction.Transaction
import com.vullpes.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class ChartsRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase):
    ChartsRoomDataSource {
    override fun getAccountBalanceByDate(
        accountId: Int,
        date1: Date,
        date2: Date
    ): Flow<Map<String, Double>> {
        val dayBalanceDao = financeAppDatabase.dayBalanceDao()
        val listDayBalanceDb = dayBalanceDao.getDayBalanceByDates(accountID = accountId, date1 = date1,date2 = date2)

        return listDayBalanceDb.map { listBalances ->
            listBalances.associate { it.date.toString() to it.finalBalance }
        }
    }


    private fun calculateFinalBalance(transactions:List<TransactionDb>):Double{
        var finalbalance = 0.0
        transactions.forEach { accountTransaction ->
            if(accountTransaction.deposit){
                finalbalance += accountTransaction.value
            }else {
                finalbalance -= accountTransaction.value
            }

        }
        return finalbalance
    }

    override fun getAllCategoryBalanceByDate(
        date1: Date,
        date2: Date
    ): Flow<com.vullpes.common.Resource<Map<String, Map<String?, Double>>>> {
        val accountDao = financeAppDatabase.accountDao()
        val accountTransactions = accountDao.loadAccountTransactionsByDate(date1 = date1,date2 = date2)

       return accountTransactions.map { transactionsByAccount ->
           val result = transactionsByAccount
                .mapValues { listTransactions ->
                    //here transactions were grouped by category then from these grouped transactions were extracted the final balance
                    listTransactions.value.groupBy { transactionDb -> transactionDb.categoryName }
                        .mapValues { transactionsGroupedByCategory -> calculateFinalBalance(transactionsGroupedByCategory.value) }
                }
                .mapKeys { listTransactions -> listTransactions.key.accountName }

           com.vullpes.common.Resource.Success(result)
        }
    }

    override fun getTransactionsByAccountAndDate(
        accountId: Int,
        date1: Date,
        date2: Date
    ): Flow<com.vullpes.common.Resource<Map<String, List<Transaction>>>> {
        val accountDao = financeAppDatabase.accountDao()
        val accountTransactions = accountDao.loadAccountTransactionsByDateAndAccountID(accountID = accountId, date1 = date1, date2 = date2)

        return accountTransactions.map { transactionsByAccount ->
            val result = transactionsByAccount
                .mapValues { listTransactions ->
                    listTransactions.value.map { it.toTransaction() }
                }
                .mapKeys { listTransactions -> listTransactions.key.accountName }

            com.vullpes.common.Resource.Success(result)
        }
    }

}