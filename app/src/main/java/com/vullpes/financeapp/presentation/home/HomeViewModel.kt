package com.vullpes.financeapp.presentation.home

import android.util.Log
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.usecases.account.ButtonSaveAccountEnabledUsecase
import com.vullpes.financeapp.domain.usecases.account.CheckIfAccountNameIsDifferentUsecase
import com.vullpes.financeapp.domain.usecases.account.CreateAccountUseCase
import com.vullpes.financeapp.domain.usecases.account.ListAccountUseCase
import com.vullpes.financeapp.domain.usecases.account.UpdateAccountUseCase
import com.vullpes.financeapp.domain.usecases.authentication.GetFlowUserUsecase
import com.vullpes.financeapp.domain.usecases.authentication.LogoutUsecase
import com.vullpes.financeapp.domain.usecases.category.ListCategoryUseCase
import com.vullpes.financeapp.domain.usecases.transaction.ButtonSaveTransactionEnabledUseCase
import com.vullpes.financeapp.domain.usecases.transaction.CreateTransactionUseCase
import com.vullpes.financeapp.domain.usecases.transaction.GetLastTransactionsByAccountUseCase
import com.vullpes.financeapp.domain.util.KindOfTransaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFlowUserUsecase: GetFlowUserUsecase,
    private val logoutUsecase: LogoutUsecase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val buttonSaveTransactionEnabledUseCase: ButtonSaveTransactionEnabledUseCase,
    private val getLastTransactionsByAccountUseCase: GetLastTransactionsByAccountUseCase,
    private val listCategoryUseCase: ListCategoryUseCase,
    private val listAccountUseCase: ListAccountUseCase,
    private val createAccountUseCase: CreateAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val buttonSaveAccountEnabledUsecase: ButtonSaveAccountEnabledUsecase,
    private val checkIfAccountNameIsDifferentUsecase: CheckIfAccountNameIsDifferentUsecase
) : ViewModel() {
    var uiState by mutableStateOf(UiStateHome())

    init {
        listAccount()
        listCategories()
        getCurrentUser()
    }


    private fun getCurrentUser() = viewModelScope.launch{
        getFlowUserUsecase.execute().collect{
            Log.e("user_e", it.toString())
            uiState = uiState.copy(
                user = it
            )
        }
    }
    fun onSaveAccount() = viewModelScope.launch(Dispatchers.IO){
        withContext(Dispatchers.Main){
            uiState = uiState.copy(
                loading = true
            )
        }
        if(uiState.accountCreateUpdate?.accountID == 0){
            uiState.accountCreateUpdate?.let {
                createAccountUseCase.invoke(it)
            }
        }else{
            uiState.accountCreateUpdate?.let {
                updateAccountUseCase.invoke(it)
            }
        }
        withContext(Dispatchers.Main){
           // val accountCreateUpDate = uiState.accountCreateUpdate
            uiState = uiState.copy(
                loading = false, openAccountModal = false, accountCreateUpdate = null
            )
        }
    }
    fun statusAccountValueChanged(accountValue:String){
        uiState = uiState.copy(accountCreateUpdate = uiState.accountCreateUpdate?.copy(accountBalance = accountValue.replace(",","").toDouble()))
        enableSaveAccountButton()
    }

    fun statusAccountNameChanged(accountName:String){
        uiState = if(checkIfAccountNameIsDifferentUsecase.execute(accountName, uiState.accounts)){
            uiState.copy(accountCreateUpdate = uiState.accountCreateUpdate?.copy(accountName = accountName))
        }else{
            uiState.copy(accountCreateUpdate = uiState.accountCreateUpdate?.copy(accountName = ""))
        }
        enableSaveAccountButton()
    }
    fun statusAccountChanged(status:Boolean){
        uiState = uiState.copy(accountCreateUpdate = uiState.accountCreateUpdate?.copy(activeAccount = status))
        enableSaveAccountButton()
    }

    fun onOpenAccountModal(account: Account? = null){
        uiState = if(account != null){
            uiState.copy(accountCreateUpdate = account, openAccountModal = true)
        }else{
            uiState.copy(accountCreateUpdate = Account(),openAccountModal = true)
        }

    }
    fun closeAccountModal(){
        uiState.copy(openAccountModal = false)
    }


    fun onCloseAccountModal(){
       uiState= uiState.copy(openAccountModal = false)
    }
    private fun enableSaveAccountButton(){
        uiState.accountCreateUpdate?.let {
            uiState = uiState.copy(buttonSaveAccountEnabled = buttonSaveAccountEnabledUsecase.execute(account = it))
        }
    }
    fun onOpenModalTransacton(accountID:Int,kindOfTransaction: KindOfTransaction ) {
        uiState = uiState.copy(
            openTransactionModal = true,
            transaction = uiState.transaction.copy(
                accountFromID = accountID,
                deposit = when(kindOfTransaction){
                    KindOfTransaction.WITHDRAW -> false
                    KindOfTransaction.DEPOSIT -> true
                    KindOfTransaction.TRANSFERENCE -> false
                },
                withdrawal = when(kindOfTransaction){
                    KindOfTransaction.WITHDRAW -> true
                    KindOfTransaction.DEPOSIT -> false
                    KindOfTransaction.TRANSFERENCE -> false
                },
                transference = when(kindOfTransaction){
                    KindOfTransaction.WITHDRAW -> false
                    KindOfTransaction.DEPOSIT -> false
                    KindOfTransaction.TRANSFERENCE -> true
                }
            )
        )
    }
    fun onValueSelected(accountValue:String) {
        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                value = accountValue.toDouble(),

            )
        )
        buttonSaveTransactionEnabled()
    }
    fun onTransactionAccountTo(accountID: Int) {
        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                accountTo = accountID,
                accountToName = uiState.accounts.first { it.accountID == accountID }.accountName
            )
        )
        buttonSaveTransactionEnabled()
    }
    fun onTransactionCategory(category: String) {
        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                categoryID = uiState.categories.first { it.nameCategory == category }.categoryID
            )
        )
        buttonSaveTransactionEnabled()
    }
    fun onTransactionName(transactionName: String) {
        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                name = transactionName
            )
        )
        buttonSaveTransactionEnabled()
    }
    fun onTransaction(kindOfTransaction: KindOfTransaction) {
        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                deposit = when(kindOfTransaction){
                    KindOfTransaction.WITHDRAW -> false
                    KindOfTransaction.DEPOSIT -> true
                    KindOfTransaction.TRANSFERENCE -> false
                },
                withdrawal = when(kindOfTransaction){
                    KindOfTransaction.WITHDRAW -> true
                    KindOfTransaction.DEPOSIT -> false
                    KindOfTransaction.TRANSFERENCE -> false
                },
                transference = when(kindOfTransaction){
                    KindOfTransaction.WITHDRAW -> false
                    KindOfTransaction.DEPOSIT -> false
                    KindOfTransaction.TRANSFERENCE -> true
                }
            )
        )
        buttonSaveTransactionEnabled()
    }

    fun onSave() = viewModelScope.launch(Dispatchers.IO){
        withContext(Dispatchers.Main){
            uiState = uiState.copy(
                loading = true
            )
        }

        createTransactionUseCase.invoke(transaction = uiState.transaction)

        withContext(Dispatchers.Main){
            uiState = uiState.copy(
                loading = false,
                openTransactionModal = false
            )
        }
    }

    private fun buttonSaveTransactionEnabled() {
        uiState = uiState.copy(buttonSaveTransactionEnabled = buttonSaveTransactionEnabledUseCase.execute(uiState.transaction) )
    }

    fun getAccountSelected(accountID: Int) = viewModelScope.launch {
        uiState = uiState.copy(accountSelected = uiState.accounts.first { it.accountID == accountID })

        getLastTransactionsByAccountUseCase.execute(accountID).collect{
            uiState = uiState.copy(transactions = it)
        }

    }

    fun logoutUser(onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(loading = true)
        }
        logoutUsecase.execute()
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(loading = false)
            onSuccess()
        }
    }

    private fun listAccount() = viewModelScope.launch {
        listAccountUseCase.operator().collect{
            uiState = uiState.copy(accounts = it)
        }
    }
    private fun listCategories() = viewModelScope.launch{
        listCategoryUseCase.invoke().collect{
            uiState = uiState.copy(categories = it)
        }
    }
}