package com.vullpes.transaction



import com.vullpes.account.Account
import com.vullpes.account.DayBalance
import com.vullpes.room.repository.transaction.TransactionRoomDataSource
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionRoomDataSource: TransactionRoomDataSource
) : TransactionRepository {


    override suspend fun createTransaction(
        transaction: com.vullpes.transaction.Transaction,
        account: Account,
        accountTo: Account?,
        transactionTransference: Transaction?,
        dayBalance: DayBalance,
        dayBalanceAccountTo: DayBalance?
    ) {
        transactionRoomDataSource.createTransaction(
            transaction,
            account,
            accountTo,
            transactionTransference,
            dayBalance,
            dayBalanceAccountTo
        )
    }

    override fun listTransactions(accountID: Int, date: Date): Flow<List<com.vullpes.transaction.Transaction>> {
        return transactionRoomDataSource.listTransactions(accountID = accountID, date = date)
    }



    override suspend fun findTransactionById(transactionID: Int): com.vullpes.transaction.Transaction? {
        return transactionRoomDataSource.findTransactionById(transactionID)
    }

    override fun getLastTransactionsByAccount(accountID: Int): Flow<List<com.vullpes.transaction.Transaction>> {
        return transactionRoomDataSource.getLastTransactionsByAccount(accountID)
    }

    override fun getAllTransactionsByAccount(accountID: Int): Flow<List<com.vullpes.transaction.Transaction>> {
        return transactionRoomDataSource.listAllTransactionsByAccount(accountID)
    }

    override fun listAllTransactionsByAccountName(transactionName: String,accountID: Int): Flow<List<com.vullpes.transaction.Transaction>> {
        return transactionRoomDataSource.listAllTransactionsByAccountName(transactionName, accountID)
    }

    override suspend fun listTransactionsByAccountAndDate(
        accountID: Int,
        date1: Date,
        date2: Date
    ): List<com.vullpes.transaction.Transaction> {
        return transactionRoomDataSource.listTransactionsByAccountAndDate(accountID, date1, date2)
    }


}