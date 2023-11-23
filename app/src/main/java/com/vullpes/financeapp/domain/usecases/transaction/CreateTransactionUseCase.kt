package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import java.util.Date
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(
        categoryID: Int,
        categoryName: String,
        accountFromID: Int,
        accountFromName: String,
        accountToID: Int? = null,
        accountToName: String? = null,
        deposit: Boolean,
        withdrawal: Boolean,
        transference:Boolean,
        value: Double,
        dateTransaction: Date
    ) {

        val transaction = Transaction(
            transactionID = 0,
            categoryID = categoryID,
            categoryName = categoryName,
            accountFromID = accountFromID,
            accountFromName = accountFromName,
            accountTo = accountToID,
            accountToName = accountToName,
            deposit = deposit,
            withdrawal = withdrawal,
            transference = transference,
            value = value,
            dateTransaction = dateTransaction
        )


        transactionRepository.createTransaction(transaction = transaction)
    }
}