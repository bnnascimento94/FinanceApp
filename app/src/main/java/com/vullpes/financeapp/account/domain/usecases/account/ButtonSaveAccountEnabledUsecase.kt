package com.vullpes.financeapp.account.domain.usecases.account

import com.vullpes.financeapp.account.domain.Account

class ButtonSaveAccountEnabledUsecase() {

    fun execute(account: Account): Boolean{
        return account.accountName.isNotBlank() && account.accountBalance != 0.0
    }
}