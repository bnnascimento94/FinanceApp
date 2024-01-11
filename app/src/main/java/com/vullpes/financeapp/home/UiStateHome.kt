package com.vullpes.financeapp.home

import com.vullpes.account.domain.Account
import com.vullpes.category.Category
import com.vullpes.transaction.Transaction
import com.vullpes.authentication.User

data class UiStateHome(
    val accountSelected: com.vullpes.account.domain.Account? = null,
    val accountCreateUpdate: com.vullpes.account.domain.Account? = null,
    val buttonSaveTransactionEnabled: Boolean = false,
    val withdrawalBlocked:Boolean = false,
    val accountNameInvalid:Boolean = false,
    val buttonSaveAccountEnabled: Boolean = false,
    val loading:Boolean = false,
    val user: com.vullpes.authentication.User? = null,
    val openTransactionModal: Boolean = false,
    val openAccountModal: Boolean = false,
    val accounts: List<com.vullpes.account.domain.Account> = emptyList(),
    val transactions: List<com.vullpes.transaction.Transaction> = emptyList(),
    val categories: List<com.vullpes.category.Category> = emptyList(),
    val transaction: com.vullpes.transaction.Transaction = com.vullpes.transaction.Transaction(),
    val valueAccount:String = "",
    val valueTransaction:String = ""
)