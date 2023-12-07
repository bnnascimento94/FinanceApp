package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.model.Account

class CheckIfAccountNameIsDifferentUsecase {
    fun execute(accountName:String, accounts:List<Account>): Boolean{
        return accounts.none { it.accountName == accountName }
    }
}