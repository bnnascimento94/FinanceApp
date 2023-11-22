package com.vullpes.financeapp.data.dataSource.room.repository.transaction

import com.vullpes.financeapp.data.dataSource.room.FinanceAppDatabase
import com.vullpes.financeapp.data.dataSource.room.entities.AccountDb
import com.vullpes.financeapp.data.dataSource.room.entities.DayBalanceDb
import com.vullpes.financeapp.data.dataSource.room.entities.toTransaction
import com.vullpes.financeapp.data.dataSource.room.entities.toTransactionDb
import com.vullpes.financeapp.domain.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class TransactionRoomDataSourceImpl @Inject constructor(private val financeAppDatabase: FinanceAppDatabase): TransactionRoomDataSource {
    override suspend fun createTransaction(transaction: Transaction) {
        try {
            val transactionDao = financeAppDatabase.transactionDao()
            val accountDao = financeAppDatabase.accountDao()
            var account = accountDao.findAccountById(accountID = transaction.accountID)
            var currentValue = account.accountBalance
            if(transaction.deposit){
                currentValue += transaction.value
            }else if(transaction.withdrawal){
                currentValue -= transaction.value
            }
            account = account.copy(accountBalance = currentValue)
            financeAppDatabase.runInTransaction{
                val transactionDb = transaction.toTransactionDb()
                transactionDao.insert(transactionDb)
                accountDao.update(account)
                updateDayBalance(account)
            }



        }catch (e:Exception){
            throw e
        }
    }
    private fun updateDayBalance(accountDb: AccountDb){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = dateFormat.parse(dateFormat.format(Date()))
        val dayBalanceDao = financeAppDatabase.dayBalanceDao()
        val dayBalanceDb = dayBalanceDao.getDayBalance(currentDate = currentDate)

        dayBalanceDb?.let { dayBalance ->
            val dayAccountUpdated = dayBalance.copy(finalBalance = accountDb.accountBalance)
            dayBalanceDao.update(dayAccountUpdated)
        }?:run {
            val dayBalanceDb = DayBalanceDb(
                dayBalanceId = 0,
                accountID = accountDb.accountID,
                date = currentDate,
                finalBalance = accountDb.accountBalance
            )
            dayBalanceDao.insert(dayBalanceDb)
        }
    }
    override fun listTransactions(accountID: Int, date: Date): Flow<List<Transaction>> {
        return try {
            val transactionDao = financeAppDatabase.transactionDao()
            return transactionDao.getTransactionByID(accountID = accountID, data = date).map { listAccountTransaction ->
                listAccountTransaction.map { it.toTransaction() }
            }
        }catch (e:Exception){
            throw e
        }
    }
    override suspend fun deleteTransaction(transactrionID: Int) {
        try {
            val transactionDao = financeAppDatabase.transactionDao()
            val accountDao = financeAppDatabase.accountDao()
            val transaction = transactionDao.getTransactionByID(transactrionID)
            transaction?.let { accountTransaction ->
                val currentTransaction = accountTransaction.transactionDb
                val transactionValue = currentTransaction.value
                var account = accountTransaction.accountDb
                var currentValue = account.accountBalance
                if(currentTransaction.deposit){
                    currentValue -= transactionValue
                }else if(currentTransaction.withdrawal){
                    currentValue += transactionValue
                }
                account = account.copy(accountBalance = currentValue)
                financeAppDatabase.runInTransaction{
                    transactionDao.delete(accountTransaction.transactionDb)
                    accountDao.update(account)
                    updateDayBalance(account)
                }

            }

        }catch (e:Exception){
            throw e
        }
    }
    override suspend fun updateTransaction(transaction: Transaction) {
        try {
            val transactionDao = financeAppDatabase.transactionDao()
            val accountDao = financeAppDatabase.accountDao()
            val transactionDbSaved = transactionDao.getTransactionByID(transactionID = transaction.transactionID)
            financeAppDatabase.runInTransaction{
                transactionDbSaved?.transactionDb?.let {
                    val valueDifference = if(it.value > transaction.value){
                        it.value - transaction.value
                    }else{
                        transaction.value - it.value
                    }
                    var account = transactionDbSaved.accountDb
                    var currentValue = account.accountBalance
                    if(it.deposit){
                        currentValue += valueDifference
                    }else if(it.withdrawal){
                        currentValue -= valueDifference
                    }
                    account = account.copy(accountBalance = currentValue)
                    accountDao.update(accountDb = account)
                }
            }
        }catch (e:Exception){
            throw e
        }
    }


}