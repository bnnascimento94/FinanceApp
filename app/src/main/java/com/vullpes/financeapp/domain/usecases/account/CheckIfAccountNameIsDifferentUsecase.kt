package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.model.Account

class CheckIfAccountNameIsDifferentUsecase {
    fun execute(account:Account, accounts:List<Account>): Boolean{

        return if(account.accountID == 0 ) {
            accounts.none { it.accountName == account.accountName}
        }else{
            accounts.any { it.accountName == account.accountName && it.accountID == account.accountID }
        }

    }
}