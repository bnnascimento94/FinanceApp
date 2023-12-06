package com.vullpes.financeapp.presentation.transactions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vullpes.financeapp.domain.usecases.transaction.ListTransactionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val listTransactionUseCase: ListTransactionUseCase
):ViewModel() {
    val uiState by mutableStateOf(UiTransactionsState())
}