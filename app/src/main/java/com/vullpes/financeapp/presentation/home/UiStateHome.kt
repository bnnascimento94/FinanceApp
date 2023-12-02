package com.vullpes.financeapp.presentation.home

import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.model.User

data class UiStateHome(
    val accountSelected:Account? = null,
    val loading:Boolean = false,
    val user: User? = null,
    val accounts: List<Account> = emptyList(),
    val transactions: List<Transaction> = emptyList()
)