package com.vullpes.financeapp.domain.usecases.account

import android.util.Log
import com.vullpes.financeapp.domain.AccountRepository
import com.vullpes.financeapp.domain.TransactionRepository
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.DayBalance
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.usecases.dayBalance.SetDayBalanceAccountUsecase
import com.vullpes.financeapp.domain.util.round
import javax.inject.Inject

class UpdateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val setDayBalanceAccountUsecase: SetDayBalanceAccountUsecase
) {

    suspend operator fun invoke(account: Account) {

        val accountSaved = accountRepository.findAccountById(account.accountID)
        var transaction: Transaction?

        accountSaved?.let { accountSaved ->
            val oldValue = accountSaved.accountBalance
            val newValue = account.accountBalance

            if (oldValue != newValue) {
                val difference = if (oldValue > newValue) {
                    oldValue - newValue
                } else {
                    newValue - oldValue
                }.round(2)


                transaction = Transaction(
                    name = "Account Update",
                    accountFromID = accountSaved.accountID,
                    accountFromName = accountSaved.accountName,
                    deposit = newValue > oldValue,
                    withdrawal = oldValue > newValue,
                    categoryID = 0,
                    categoryName = "",
                    value = difference
                )
                val dayBalance: DayBalance = setDayBalanceAccountUsecase.execute(account)
                transactionRepository.createTransaction(
                    transaction = transaction!!,
                    account = account,
                    accountTo = null,
                    transactionTransference = null,
                    dayBalance = dayBalance,
                    null
                )
            }
        } ?: accountRepository.updateAccount(account = account)


    }


}