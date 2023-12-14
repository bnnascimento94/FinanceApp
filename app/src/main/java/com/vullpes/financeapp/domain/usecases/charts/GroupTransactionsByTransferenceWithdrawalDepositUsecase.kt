package com.vullpes.financeapp.domain.usecases.charts

import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Transaction
import java.util.Date
import javax.inject.Inject

class GroupTransactionsByTransferenceWithdrawalDepositUsecase @Inject constructor(private val transactionRepository: TransactionRepository) {
    suspend fun execute(accountID: Int, date1: Date, date2: Date) : Map<String,Double>{
        val transactions = transactionRepository.listTransactionsByAccountAndDate(accountID, date1, date2)
        val groupedByTransference = calculateFinalBalance(transactions.filter { it.transference })
        val groupedByWithdraw = calculateFinalBalance(transactions.filter { it.withdrawal })
        val groupedByDeposit = calculateFinalBalance(transactions.filter { it.deposit })

        return if(groupedByDeposit == 0.0 && groupedByWithdraw == 0.0 && groupedByTransference ==0.0){
            mapOf()
        }else{
            mapOf(
                "Deposit" to groupedByDeposit,
                "Withdraw" to groupedByWithdraw,
                "Transference" to groupedByTransference,
            )
        }
    }

    private fun calculateFinalBalance(transactions: List<Transaction>?): Double {
        var finalbalance = 0.0
        transactions?.forEach { accountTransaction ->
                finalbalance += accountTransaction.value
        }
        return finalbalance
    }


}