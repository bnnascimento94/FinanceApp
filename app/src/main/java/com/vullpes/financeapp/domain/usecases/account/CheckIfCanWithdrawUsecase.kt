package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction

class CheckIfCanWithdrawUsecase() {

    fun execute(account: Account, transaction: Transaction):Boolean{
        return !transaction.withdrawal || account.accountBalance >= transaction.value
    }

}