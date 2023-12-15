package com.vullpes.financeapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.R
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Category
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.util.CurrencyAmountInputVisualTransformation
import com.vullpes.financeapp.domain.util.KindOfTransaction
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetTransactions(
    buttonSaveEnabled: Boolean,
    withdrawalBlocked:Boolean,
    transaction: Transaction,
    inputValueTransaction:String,
    accountSelected: Account,
    listCategory: List<Category>,
    listAccounts: List<Account>,
    onKindOfTransactionSelected: (KindOfTransaction) -> Unit,
    onTransactionNameChanged: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onAccountSelected: (Int) -> Unit,
    onValueTransaction: (String) -> Unit,
    onDismiss: (SheetState) -> Unit,
    onSave: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = { onDismiss(modalBottomSheetState) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },

        ) {
        TransactionScreen(
            transaction = transaction,
            inputValueTransaction= inputValueTransaction,
            listCategory = listCategory,
            listAccounts = listAccounts,
            onKindOfTransactionSelected = onKindOfTransactionSelected,
            onTransactionNameChanged= onTransactionNameChanged,
            onCategorySelected = onCategorySelected,
            onAccountSelected = onAccountSelected,
            onValueTransaction = onValueTransaction,
            onSave = onSave,
            buttonSaveEnabled = buttonSaveEnabled,
            accountSelected = accountSelected,
            withdrawalBlocked = withdrawalBlocked

        )
    }


}



@Composable
fun TransactionScreen(
    inputValueTransaction:String,
    buttonSaveEnabled: Boolean,
    withdrawalBlocked:Boolean,
    transaction: Transaction,
    accountSelected: Account,
    listCategory: List<Category>,
    listAccounts: List<Account>,
    onKindOfTransactionSelected: (KindOfTransaction) -> Unit,
    onTransactionNameChanged: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onAccountSelected: (Int) -> Unit,
    onValueTransaction: (String) -> Unit,
    onSave: () -> Unit
) {

    var text by remember { mutableStateOf("2.500,00") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Text(
            text = stringResource(R.string.transaction),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(modifier = Modifier.padding(6.dp), text = stringResource(R.string.kind_of_transaction))
        Row(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = transaction.withdrawal, onClick = {
                    onKindOfTransactionSelected(KindOfTransaction.WITHDRAW)
                }
                )
                Text(text = stringResource(R.string.withdraw))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = transaction.deposit, onClick = {
                    onKindOfTransactionSelected(KindOfTransaction.DEPOSIT)
                })
                Text(text = stringResource(R.string.deposit))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = transaction.transference, onClick = {
                    onKindOfTransactionSelected(KindOfTransaction.TRANSFERENCE)
                })
                Text(text = stringResource(R.string.transference))
            }
        }


        if(!transaction.transference){
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 0.dp, start = 8.dp, end = 8.dp)
                    .fillMaxWidth(),
                label = { Text(stringResource(R.string.transaction_name)) },
                value = transaction.name,
                onValueChange = onTransactionNameChanged,
            )

            DropDownMenu(
                listItems = listCategory.map { it.nameCategory?:"" },
                modifier = Modifier.padding(top =8.dp, bottom = 0.dp, start = 8.dp, end = 8.dp),
                label = stringResource(R.string.category),
                onItemSelected = onCategorySelected)
        }
        
        if (transaction.transference) {
            DropDownMenu(
                listItems = listAccounts.map { it.accountName?:"" }.filter { it != accountSelected.accountName },
                modifier = Modifier.padding(top =8.dp, bottom = 0.dp, start = 8.dp, end = 8.dp),
                label = stringResource(R.string.account),
                onItemSelected = { accountSelected ->
                    val account = listAccounts.first { it.accountName == accountSelected }
                    onAccountSelected(account.accountID)
                })
        }

        //
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(text = stringResource(R.string.value)) },
            value = inputValueTransaction,
            onValueChange = {
                val value = if (it.startsWith("0")) {
                    ""
                } else {
                    it
                }
                onValueTransaction(value)
            },
            prefix = { Text(text = "$") },
            supportingText = {
                 if(withdrawalBlocked){ Text(text = "You don't have sufficient amount") }
            },
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            )
        )

        Button(
            enabled = buttonSaveEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), onClick = onSave) {
            Text(text = stringResource(id = R.string.save))
        }


    }

}



@Preview(showBackground = true)
@Composable
fun PreviewBottomSheetDialogTransactions() {
    TransactionScreen(
        transaction = Transaction(
            transactionID = 1,
            name = "Transaction 2",
            categoryID = 1,
            categoryName = "Deposit",
            accountFromID = 1,
            accountFromName = "Personal Account",
            deposit = false,
            withdrawal = true,
            transference = false,
            value = 150.00,
            dateTransaction = Date()
        ),
        listCategory = emptyList(),
        listAccounts = listOf(
            Account(
                1,
                "Casa",
                accountBalance = 1500.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            Account(
                2,
                "Personal account",
                accountBalance = 1400.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            Account(
                3,
                "Personal account",
                accountBalance = 1300.00,
                activeAccount = false,
                dataCreationAccount = Date()
            )
        ),
        onKindOfTransactionSelected = {},
        onTransactionNameChanged = {},
        onCategorySelected = {},
        onAccountSelected = {},
        onValueTransaction = {},
        onSave = {},
        buttonSaveEnabled = true,
        accountSelected = Account(),
        withdrawalBlocked = false,
        inputValueTransaction = ""

    )
}