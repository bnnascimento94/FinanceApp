package com.vullpes.financeapp.presentation.transactions

import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.util.Resource

data class UiTransactionsState(
    val textPesquisa:String = "",
    val errorText:String = "",
    val searchBarActive: Boolean = false,
    val transactions:Resource<List<Transaction>> = Resource.Loading()
)