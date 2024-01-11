package com.vullpes.account.usecases.account

import com.vullpes.account.Account

class ButtonSaveAccountEnabledUsecase() {

    fun execute(account: Account): Boolean{
        return account.accountName.isNotBlank() && account.accountBalance != 0.0
    }
}