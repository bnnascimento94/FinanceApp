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
            var account = accountDao.findAccountById(accountID = transaction.accountFromID)
            var currentValue = account.accountBalance

            var accountTo: AccountDb? = transaction.accountTo?.let { accountDao.findAccountById(accountID = it) }
            var accountToValue: Double? = accountTo?.accountBalance

            if(transaction.deposit){
                currentValue += transaction.value
            }else if(transaction.withdrawal){
                currentValue -= transaction.value
            }else if(transaction.transference){
                currentValue -= transaction.value
                accountToValue = accountToValue?.plus(transaction.value)
            }
            account = account.copy(accountBalance = currentValue)
            accountTo = accountTo?.copy(accountBalance = accountToValue!!)
            financeAppDatabase.runInTransaction{
                val transactionDb = transaction.toTransactionDb()
                transactionDao.insert(transactionDb)
                accountDao.update(account)
                updateDayBalance(account)
                if(transaction.transference){
                    accountDao.update(accountTo!!)
                    updateDayBalance(accountTo)
                }
            }
        }catch (e:Exception){
            throw e
        }
    }
    private fun updateDayBalance(accountDb: AccountDb){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = dateFormat.parse(dateFormat.format(Date()))
        val dayBalanceDao = financeAppDatabase.dayBalanceDao()
        val dayBalanceDb = dayBalanceDao.getDayBalance(accountID = accountDb.accountID, currentDate = currentDate)

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
                var account = accountTransaction.accountFromDb
                var currentValue = account.accountBalance

                var accountTo: AccountDb? = currentTransaction.accountToID?.let { accountDao.findAccountById(accountID = it) }
                var accountToValue: Double? = accountTo?.accountBalance

                if(currentTransaction.deposit){
                    currentValue -= transactionValue
                }else if(currentTransaction.withdrawal){
                    currentValue += transactionValue
                }else if(currentTransaction.transference){
                    currentValue += currentTransaction.value
                    accountToValue = accountToValue?.minus(currentTransaction.value)
                }

                account = account.copy(accountBalance = currentValue)
                accountTo = accountTo?.copy(accountBalance = accountToValue!!)
                financeAppDatabase.runInTransaction{
                    transactionDao.delete(accountTransaction.transactionDb)
                    accountDao.update(account)
                    updateDayBalance(account)
                    if(currentTransaction.transference){
                        accountDao.update(accountTo!!)
                        updateDayBalance(accountTo)
                    }
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
                    var account = transactionDbSaved.accountFromDb
                    var currentValue = account.accountBalance

                    var accountTo: AccountDb? = it.accountToID?.let { accountDao.findAccountById(accountID = it) }
                    var accountToValue: Double? = accountTo?.accountBalance
                    if(it.deposit){
                        currentValue += valueDifference
                    }else if(it.withdrawal){
                        currentValue -= valueDifference
                    }else if(transaction.transference){
                        currentValue -= transaction.value
                        accountToValue = accountToValue?.plus(transaction.value)
                        accountTo = accountTo?.copy(accountBalance = accountToValue!!)
                    }
                    account = account.copy(accountBalance = currentValue)
                    accountDao.update(accountDb = account)
                    updateDayBalance(account)

                    if(transaction.transference){
                        accountDao.update(accountTo!!)
                        updateDayBalance(accountTo)
                    }
                }
            }
        }catch (e:Exception){
            throw e
        }
    }


}