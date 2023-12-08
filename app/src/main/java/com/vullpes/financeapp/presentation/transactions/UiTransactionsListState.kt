package com.vullpes.financeapp.presentation.transactions

import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Resource

data class UiTransactionsListState(
    val accountId: Int = 0,
    val loading:Boolean = false,
    val textSearch:String = "",
    val errorText:String = "",
    val searchBarActive: Boolean = false,
    val transactions:List<Transaction> = emptyList()
)