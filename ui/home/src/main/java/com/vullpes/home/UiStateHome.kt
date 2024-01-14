package com.vullpes.home

import com.vullpes.account.Account
import com.vullpes.category.Category
import com.vullpes.transaction.Transaction

data class UiStateHome(
    val accountSelected: Account? = null,
    val accountCreateUpdate: Account? = null,
    val buttonSaveTransactionEnabled: Boolean = false,
    val withdrawalBlocked:Boolean = false,
    val accountNameInvalid:Boolean = false,
    val buttonSaveAccountEnabled: Boolean = false,
    val loading:Boolean = false,
    val user: com.vullpes.authentication.User? = null,
    val openTransactionModal: Boolean = false,
    val openAccountModal: Boolean = false,
    val accounts: List<Account> = emptyList(),
    val transactions: List<Transaction> = emptyList(),
    val categories: List<Category> = emptyList(),
    val transaction: Transaction = Transaction(),
    val valueAccount:String = "",
    val valueTransaction:String = ""
)