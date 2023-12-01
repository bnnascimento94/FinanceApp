package com.vullpes.financeapp.presentation.home

import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction

data class UiStateHome(
    val accountSelected:Account? = null,
    val loading:Boolean = false,
    val accounts: List<Account> = emptyList(),
    val transactions: List<Transaction> = emptyList()
)