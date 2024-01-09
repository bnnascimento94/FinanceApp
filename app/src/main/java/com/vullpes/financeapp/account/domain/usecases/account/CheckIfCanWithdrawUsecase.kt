package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.Account
import com.vullpes.financeapp.transaction.domain.Transaction

class CheckIfCanWithdrawUsecase() {

    fun execute(account: Account, transaction: Transaction):Boolean{
        return !transaction.withdrawal || account.accountBalance >= transaction.value
    }

}