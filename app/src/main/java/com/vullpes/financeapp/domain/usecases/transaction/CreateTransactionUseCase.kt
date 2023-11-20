package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import java.util.Date
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(
        categoryID: Int,
        categoryName: String,
        accountID: Int,
        accountName: String,
        deposit: Boolean,
        withdrawal: Boolean,
        value: Double,
        dateTransaction: Date
    ) {

        val transaction = Transaction(
            transactionID = 0,
            categoryID = categoryID,
            categoryName = categoryName,
            accountID = accountID,
            accountName = accountName,
            deposit = deposit,
            withdrawal = withdrawal,
            value = value,
            dateTransaction = dateTransaction

        )


        transactionRepository.createTransaction(transaction = transaction)
    }
}