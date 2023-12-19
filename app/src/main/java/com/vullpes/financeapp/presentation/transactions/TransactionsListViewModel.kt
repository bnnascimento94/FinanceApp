package com.vullpes.financeapp.presentation.transactions

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.financeapp.domain.usecases.transaction.ListAllTransactionsByAccountUsecase
import com.vullpes.financeapp.domain.usecases.transaction.ListAllTransactionsByNameUsecase
import com.vullpes.financeapp.domain.usecases.transaction.ListTransactionByAccountDateUseCase
import com.vullpes.financeapp.navigation.Constants.ACCOUNTID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class TransactionsListViewModel @Inject constructor(
    private val listTransactionByAccountDateUseCase: ListTransactionByAccountDateUseCase,
    private val listAllTransactionsByAccountUsecase: ListAllTransactionsByAccountUsecase,
    private val listAllTransactionsByNameUsecase: ListAllTransactionsByNameUsecase,
    private val savedStateHandle: SavedStateHandle,
):ViewModel() {
    var uiState by mutableStateOf(UiTransactionsListState())
    private set

    private lateinit var alltransactionsByAccount: Job
    private lateinit var alltransactionsByAccountAndDate: Job
    private lateinit var alltransactionsByName: Job
    init {
        uiState = uiState.copy(
            accountId = savedStateHandle.get<Int>(
                key = ACCOUNTID
            )?:0
        )
        getSearchByAccount()
    }

    fun onSearchItem(){
        uiState =  uiState.copy(searchBarActive = false)
    }

    fun onChangeStatus(activeStatus: Boolean){
        uiState =  uiState.copy(searchBarActive = activeStatus)
    }

    fun itemPesquisa(textSearch: String){
        uiState =  uiState.copy(textSearch = textSearch)
        if(textSearch.isBlank()){
            getSearchByAccount()
        }else{
            getSearchByName()
        }

    }


    @SuppressLint("SimpleDateFormat")
    fun getSearchByDate(date: LocalDate) {
        alltransactionsByAccountAndDate = viewModelScope.launch{
            if(::alltransactionsByAccount.isInitialized){
                alltransactionsByAccount.cancelAndJoin()
            }
            if(::alltransactionsByName.isInitialized){
                alltransactionsByName.cancelAndJoin()
            }
            val searchDate = SimpleDateFormat("yyyy-MM-dd").parse(date.toString());
            listTransactionByAccountDateUseCase.invoke(uiState.accountId, searchDate).collect{
                uiState = uiState.copy(transactions = it)
            }
        }
    }

    private fun getSearchByAccount() {
        alltransactionsByAccount = viewModelScope.launch{
            if(::alltransactionsByAccountAndDate.isInitialized){
                alltransactionsByAccountAndDate.cancelAndJoin()
            }
            if(::alltransactionsByName.isInitialized){
                alltransactionsByName.cancelAndJoin()
            }
            listAllTransactionsByAccountUsecase.execute(uiState.accountId).collect{
                uiState = uiState.copy(transactions = it)
            }
        }
    }

    fun getSearchByName() {
        alltransactionsByName = viewModelScope.launch{
            if(::alltransactionsByAccountAndDate.isInitialized){
                alltransactionsByAccountAndDate.cancelAndJoin()
            }
            if(::alltransactionsByAccount.isInitialized){
                alltransactionsByAccount.cancelAndJoin()
            }
            listAllTransactionsByNameUsecase.execute(uiState.textSearch, uiState.accountId).collect{
                uiState = uiState.copy(transactions = it)
            }
        }
    }
}