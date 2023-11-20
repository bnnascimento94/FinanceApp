package com.vullpes.financeapp.domain.usecases.transaction

import com.vullpes.financeapp.domain.TransactionRepository
import javax.inject.Inject

class DeleteTransactionUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    suspend operator fun invoke(transactionID:Int){
        transactionRepository.deleteTransaction(transactionID)
    }
}