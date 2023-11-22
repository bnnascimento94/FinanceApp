package com.vullpes.financeapp.data.dataSource.room.repository.charts

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.TransactionDb
import com.vullpes.financeapp.util.Months
import com.vullpes.financeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class ChartsRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase): ChartsRoomDataSource {
    override fun getAccountBalanceByDate(
        accountId: Int,
        date1: Date,
        date2: Date
    ): Flow<Resource<Map<String, Double>>> {
        val dayBalanceDao = financeAppDatabase.dayBalanceDao()
        val listDayBalanceDb = dayBalanceDao.getDayBalanceByDates(accountID = accountId, date1 = date1,date2 = date2)

        return listDayBalanceDb.map { listBalances ->
            Resource.Success(listBalances.associate { it.date.toString(it.date) to it.finalBalance })
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

    override fun getAccountBalanceByMonth(
        accountId: Int,
        month: Months
    ): Flow<Resource<Map<String, Double>>> {
        TODO("Not yet implemented")
    }

    override fun getAllCategoryBalanceByDate(
        date1: Date,
        date2: Date
    ): Flow<Resource<Map<String, Map<String, Double>>>> {
      /**  val accountTransactions = accountDao.loadAccountTransactionsByDate(accountID = accountId, date1 = date1,date2 = date2)


        return accountTransactions.map { transactionsByAccount ->
            Resource.Success(transactionsByAccount.mapValues { calculateFinalBalance(it.value) }.mapKeys { it.key.accountName })
        }**/

        TODO("Not yet implemented")
    }

    override fun getAllCategoryBalanceByMonth(month: Months): Flow<Resource<Map<String, Map<String, Double>>>> {
        TODO("Not yet implemented")
    }

    private fun Date.toString(date:Date):String{
        val formatter = SimpleDateFormat("yyyy.MM.dd")
        return formatter.format(date)
    }
}