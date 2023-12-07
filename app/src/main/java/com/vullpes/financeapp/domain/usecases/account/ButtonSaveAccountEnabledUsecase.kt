package com.vullpes.financeapp.domain.usecases.account

import com.vullpes.financeapp.domain.model.Account

class ButtonSaveAccountEnabledUsecase() {

    fun execute(account: Account): Boolean{
        return account.accountName != null && account.accountBalance != null
    }
}