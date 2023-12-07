package com.vullpes.financeapp.presentation.home

import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Category
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.model.User

data class UiStateHome(
    val accountSelected:Account? = null,
    val accountCreateUpdate:Account? = null,
    val buttonSaveTransactionEnabled: Boolean = false,
    val buttonSaveAccountEnabled: Boolean = false,
    val loading:Boolean = false,
    val user: User? = null,
    val openTransactionModal: Boolean = false,
    val openAccountModal: Boolean = false,
    val accounts: List<Account> = emptyList(),
    val transactions: List<Transaction> = emptyList(),
    val categories: List<Category> = emptyList(),
    val transaction: Transaction = Transaction()
)