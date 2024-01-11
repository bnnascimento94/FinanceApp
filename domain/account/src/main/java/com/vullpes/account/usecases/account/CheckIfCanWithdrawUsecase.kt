package com.vullpes.account.usecases.account

import com.vullpes.account.Account
import com.vullpes.transaction.Transaction

class CheckIfCanWithdrawUsecase() {

    fun execute(account: Account, transaction: Transaction):Boolean{
        return !transaction.withdrawal || account.accountBalance >= transaction.value
    }

}