package com.vullpes.transaction

import com.vullpes.financeapp.transaction.domain.Transaction
import java.time.LocalDate

data class UiTransactionsListState(
    val accountId: Int = 0,
    val loading:Boolean = false,
    val textSearch:String = "",
    val errorText:String = "",
    val searchBarActive: Boolean = false,
    val transactions:Map<LocalDate, List<Transaction>> = mapOf()
)