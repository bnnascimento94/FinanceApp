package com.vullpes.account.usecases.account

import com.vullpes.account.Account
import com.vullpes.account.AccountRepository
import com.vullpes.charts.DayBalance
import com.vullpes.charts.usecase.SetDayBalanceAccountUsecase
import com.vullpes.transaction.Transaction
import com.vullpes.transaction.TransactionRepository
import com.vullpes.util.domain.currency.round
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
            }else{
                accountRepository.updateAccount(account = account)
            }
        } ?: accountRepository.updateAccount(account = account)


    }


}