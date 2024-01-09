package com.vullpes.financeapp.presentation.home

import com.vullpes.financeapp.account.domain.Account
import com.vullpes.financeapp.category.domain.Category
import com.vullpes.financeapp.transaction.domain.Transaction
import com.vullpes.financeapp.authentication.domain.User

data class UiStateHome(
    val accountSelected: Account? = null,
    val accountCreateUpdate: Account? = null,
    val buttonSaveTransactionEnabled: Boolean = false,
    val withdrawalBlocked:Boolean = false,
    val accountNameInvalid:Boolean = false,
    val buttonSaveAccountEnabled: Boolean = false,
    val loading:Boolean = false,
    val user: User? = null,
    val openTransactionModal: Boolean = false,
    val openAccountModal: Boolean = false,
    val accounts: List<Account> = emptyList(),
    val transactions: List<Transaction> = emptyList(),
    val categories: List<Category> = emptyList(),
    val transaction: Transaction = Transaction(),
    val valueAccount:String = "",
    val valueTransaction:String = ""
)