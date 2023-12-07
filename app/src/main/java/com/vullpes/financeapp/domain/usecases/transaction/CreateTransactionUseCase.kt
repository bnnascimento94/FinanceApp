package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import java.util.Date
import javax.inject.Inject

class CreateTransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(
        transaction: Transaction
    ) {
        transactionRepository.createTransaction(transaction = transaction)
    }
}