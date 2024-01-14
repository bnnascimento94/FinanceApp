package com.vullpes.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vullpes.account.Account
import com.vullpes.account.usecases.account.ButtonSaveAccountEnabledUsecase
import com.vullpes.account.usecases.account.CheckIfAccountNameIsDifferentUsecase
import com.vullpes.transaction.usecases.CheckIfCanWithdrawUsecase
import com.vullpes.transaction.usecases.CreateAccountUseCase
import com.vullpes.account.usecases.account.ListAccountUseCase
import com.vullpes.transaction.usecases.UpdateAccountUseCase
import com.vullpes.authentication.usecases.ClearAllSessionDataUsecase
import com.vullpes.authentication.usecases.GetFlowUserUsecase
import com.vullpes.authentication.usecases.LogoutUsecase
import com.vullpes.category.usecases.ListCategoryUseCase
import com.vullpes.transaction.usecases.ButtonSaveTransactionEnabledUseCase
import com.vullpes.transaction.usecases.CreateTransactionUseCase
import com.vullpes.transaction.usecases.GetLastTransactionsByAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFlowUserUsecase: GetFlowUserUsecase,
    private val logoutUsecase: LogoutUsecase,
    private val clearAllSessionDataUsecase: ClearAllSessionDataUsecase,
    private val checkIfCanWithdrawUsecase: CheckIfCanWithdrawUsecase,
    private val createTransactionUseCase: CreateTransactionUseCase,
    private val buttonSaveTransactionEnabledUseCase: ButtonSaveTransactionEnabledUseCase,
    private val getLastTransactionsByAccountUseCase: GetLastTransactionsByAccountUseCase,
    private val listCategoryUseCase: ListCategoryUseCase,
    private val listAccountUseCase: ListAccountUseCase,
    private val createAccountUseCase: CreateAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase,
    private val buttonSaveAccountEnabledUsecase: ButtonSaveAccountEnabledUsecase,
    private val checkIfAccountNameIsDifferentUsecase: CheckIfAccountNameIsDifferentUsecase,
    private val createDefaultCategoriesUsecase: com.vullpes.category.usecases.CreateDefaultCategoriesUsecase
) : ViewModel() {
    var uiState by mutableStateOf(UiStateHome())

    init {
        getCurrentUser()
        listCategories()
        createCategoriesDefault()
    }

    private fun createCategoriesDefault() = viewModelScope.launch(Dispatchers.IO) {
        createDefaultCategoriesUsecase.execute()
    }

    private fun getCurrentUser() = viewModelScope.launch {
        getFlowUserUsecase.execute().collect {
            uiState = uiState.copy(
                user = it
            )
            listAccount(it.id)
        }
    }

    fun onSaveAccount() = viewModelScope.launch(Dispatchers.IO) {
        uiState.accountCreateUpdate?.let {account ->
            withContext(Dispatchers.Main) {
                uiState = uiState.copy(
                    loading = true
                )
            }

            if (checkIfAccountNameIsDifferentUsecase.execute(account, uiState.accounts)) {
                if (account.accountID == 0) {
                    createAccountUseCase.invoke(account.apply { this.userID = uiState.user?.id?:0 })
                } else {
                    updateAccountUseCase.invoke(account.apply { this.userID = uiState.user?.id?:0 })
                }
                withContext(Dispatchers.Main) {
                    val accountCreateUpDate = uiState.accountCreateUpdate
                    uiState = uiState.copy(
                        loading = false,
                        openAccountModal = false,
                        accountCreateUpdate = null,
                        buttonSaveAccountEnabled = false,
                        accountNameInvalid = false,
                        accountSelected = uiState.accounts.find { it.accountName == accountCreateUpDate?.accountName }
                    )
                }
            } else{
                withContext(Dispatchers.Main) {
                    uiState = uiState.copy(
                        loading = false,
                        accountNameInvalid = true
                    )
                }
            }
        }


    }

    fun statusAccountValueChanged(accountValue: String) {
        uiState = uiState.copy(valueAccount = accountValue)
        uiState = uiState.copy(
            accountCreateUpdate = uiState.accountCreateUpdate?.copy(
                accountBalance = if(accountValue.isBlank()) 0.00 else com.vullpes.common.domain.currency.CurrencyAmountInputVisualTransformation()
                    .filter(
                    AnnotatedString(
                        accountValue
                    )
                ).text.toString().replace(",", "").toDouble()
            )
        )
        enableSaveAccountButton()
    }

    fun statusAccountNameChanged(accountName: String) {
        uiState = uiState.copy(accountCreateUpdate = uiState.accountCreateUpdate?.copy(accountName = accountName))
        enableSaveAccountButton()
    }

    fun statusAccountChanged(status: Boolean) {
        uiState =
            uiState.copy(accountCreateUpdate = uiState.accountCreateUpdate?.copy(activeAccount = status))
        enableSaveAccountButton()
    }

    fun onOpenAccountModal(account: Account? = null) {
        uiState = if (account != null) {
            uiState.copy(accountCreateUpdate = account, openAccountModal = true, valueAccount = account.accountBalance.toString().replace(",", "").replace(".", ""))
        } else {
            uiState.copy(accountCreateUpdate = Account(), openAccountModal = true, valueAccount = "")
        }

    }

    fun closeAccountModal() {
        uiState = uiState.copy(
            openAccountModal = false,
            accountNameInvalid = false,
            accountCreateUpdate = null,
            buttonSaveAccountEnabled = false,
            valueAccount = ""
        )
    }


    private fun enableSaveAccountButton() {
        uiState.accountCreateUpdate?.let {
            uiState = uiState.copy(
                buttonSaveAccountEnabled = buttonSaveAccountEnabledUsecase.execute(account = it)
            )
        }
    }

    fun onCloseModalTransaction() {
        uiState = uiState.copy(
            openTransactionModal = false,
            transaction = com.vullpes.transaction.Transaction(),
            buttonSaveTransactionEnabled = false,
            withdrawalBlocked = false,
            valueTransaction = ""
        )
    }

    fun onOpenModalTransacton(accountID: Int, kindOfTransaction: com.vullpes.common.domain.KindOfTransaction) {
        uiState = uiState.copy(
            openTransactionModal = true,
            transaction = uiState.transaction.copy(
                accountFromID = accountID,
                deposit = when (kindOfTransaction) {
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW -> false
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT -> true
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE -> false
                },
                withdrawal = when (kindOfTransaction) {
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW -> true
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT -> false
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE -> false
                },
                transference = when (kindOfTransaction) {
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW -> false
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT -> false
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE -> true
                }
            )
        )
    }

    fun onValueSelected(accountValue: String) {
        uiState = uiState.copy(valueTransaction = accountValue)

        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                value = com.vullpes.common.domain.currency.CurrencyAmountInputVisualTransformation()
                    .filter(
                    AnnotatedString(
                        accountValue
                    )
                ).text.toString().replace(",", "").toDouble(),
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

    fun onTransaction(kindOfTransaction: com.vullpes.common.domain.KindOfTransaction) {
        uiState = uiState.copy(
            transaction = uiState.transaction.copy(
                deposit = when (kindOfTransaction) {
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW -> false
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT -> true
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE -> false
                },
                withdrawal = when (kindOfTransaction) {
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW -> true
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT -> false
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE -> false
                },
                transference = when (kindOfTransaction) {
                    com.vullpes.common.domain.KindOfTransaction.WITHDRAW -> false
                    com.vullpes.common.domain.KindOfTransaction.DEPOSIT -> false
                    com.vullpes.common.domain.KindOfTransaction.TRANSFERENCE -> true
                }
            )
        )
        buttonSaveTransactionEnabled()
    }

    fun onSave() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(
                loading = true
            )
        }
        if (checkIfCanWithdrawUsecase.execute(uiState.accountSelected!!, uiState.transaction)) {

            createTransactionUseCase.invoke(transaction = uiState.transaction)

            withContext(Dispatchers.Main) {
                uiState = uiState.copy(
                    loading = false,
                    openTransactionModal = false,
                    buttonSaveTransactionEnabled = false,
                    withdrawalBlocked = false,
                    transaction = com.vullpes.transaction.Transaction()
                )
            }
        } else {
            uiState = uiState.copy(
                loading = false,
                withdrawalBlocked = true
            )
        }
    }

    private fun buttonSaveTransactionEnabled() {
        uiState = uiState.copy(
            buttonSaveTransactionEnabled = buttonSaveTransactionEnabledUseCase.execute(uiState.transaction)
        )
    }

    fun getAccountSelected(accountID: Int) = viewModelScope.launch {
        uiState =
            uiState.copy(accountSelected = uiState.accounts.first { it.accountID == accountID })
        listTransactions()
    }

    fun logoutUser(onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(loading = true)
        }
        logoutUsecase.execute()
        clearAllSessionDataUsecase.execute()
        withContext(Dispatchers.Main) {
            uiState = uiState.copy(loading = false)
            onSuccess()
        }
    }

    private fun listAccount(userID: Int) = viewModelScope.launch {
        listAccountUseCase.operator(userID).collect {
            uiState = uiState.copy(accounts = it)
            if (uiState.accountSelected == null && it.isNotEmpty()) {
                uiState = uiState.copy(accountSelected = it.first())
                listTransactions()
            }
        }
    }

    private fun listCategories() = viewModelScope.launch {
        listCategoryUseCase.invoke().collect {
            uiState = uiState.copy(categories = it)
        }
    }

    private fun listTransactions() = viewModelScope.launch {
        uiState.accountSelected?.accountID?.let { accountID ->
            getLastTransactionsByAccountUseCase.execute(accountID).collect { listTransactions ->
                uiState = uiState.copy(transactions = listTransactions)
            }
        }
    }
}