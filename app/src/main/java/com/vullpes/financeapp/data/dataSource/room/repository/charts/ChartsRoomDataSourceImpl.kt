package com.vullpes.financeapp.data.dataSource.room.repository.charts

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.TransactionDb
import com.vullpes.financeapp.data.dataSource.room.entities.toTransaction
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import javax.inject.Inject

class ChartsRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase): ChartsRoomDataSource {
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
    ): Flow<Resource<Map<String, Map<String?, Double>>>> {
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

           Resource.Success(result)
        }
    }

    override fun getTransactionsByAccountAndDate(
        accountId: Int,
        date1: Date,
        date2: Date
    ): Flow<Resource<Map<String, List<Transaction>>>> {
        val accountDao = financeAppDatabase.accountDao()
        val accountTransactions = accountDao.loadAccountTransactionsByDateAndAccountID(accountID = accountId, date1 = date1, date2 = date2)

        return accountTransactions.map { transactionsByAccount ->
            val result = transactionsByAccount
                .mapValues { listTransactions ->
                    listTransactions.value.map { it.toTransaction() }
                }
                .mapKeys { listTransactions -> listTransactions.key.accountName }

            Resource.Success(result)
        }
    }

}