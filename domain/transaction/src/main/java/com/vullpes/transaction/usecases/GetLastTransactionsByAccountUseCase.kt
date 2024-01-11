package com.vullpes.transaction.usecases

import com.vullpes.transaction.TransactionRepository
import javax.inject.Inject

class GetLastTransactionsByAccountUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    fun execute(accountID: Int) = transactionRepository.getLastTransactionsByAccount(accountID = accountID)
}