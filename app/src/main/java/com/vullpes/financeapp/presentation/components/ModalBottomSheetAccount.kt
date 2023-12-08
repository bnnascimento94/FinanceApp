package com.vullpes.financeapp.presentation.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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
import com.vullpes.financeapp.domain.util.CurrencyAmountInputVisualTransformation
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetAccount(
    activateSaveAccount: Boolean,
    account: Account?,
    onChangeAccountStatus: (Boolean) -> Unit,
    onChangeAccountName: (String) -> Unit,
    onChangeAccountValue: (String) -> Unit,
    onSave: () -> Unit,
    onDismiss: (SheetState) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss(modalBottomSheetState) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },

        ) {
        CreateAccount(
            activateSaveAccount = activateSaveAccount,
            account = account,
            onChangeAccountStatus = onChangeAccountStatus,
            onChangeAccountName = onChangeAccountName,
            onChangeAccountValue = onChangeAccountValue,
            onSave = onSave
        )
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(
    activateSaveAccount: Boolean,
    account: Account?,
    onChangeAccountStatus: (Boolean) -> Unit,
    onChangeAccountName: (String) -> Unit,
    onChangeAccountValue: (String) -> Unit,
    onSave: () -> Unit
) {

    var selectedOption by remember { mutableStateOf("Option1") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.account),
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = stringResource(R.string.account_active))
            Switch(
                checked = account?.activeAccount ?: true,
                onCheckedChange = onChangeAccountStatus
            )
        }


        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(stringResource(R.string.account_name)) },
            value = account?.accountName ?: "",
            onValueChange = onChangeAccountName,
        )



        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(text = stringResource(R.string.total_balance)) },
            value = account?.accountBalance?.toString()?.replace(",", "")?.replace(".", "") ?: "0",
            onValueChange = onChangeAccountValue,
            prefix = { Text(text = "$") },
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Button(
            enabled = activateSaveAccount,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), onClick = onSave
        ) {
            Text(text = stringResource(R.string.save))
        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewCreateAccount() {
    CreateAccount(
        activateSaveAccount = true,
        account = Account(
            accountID = 1,
            accountName = "Conta Pessoal",
            accountBalance = 120.00,
            dataCreationAccount = Date(),
            activeAccount = false
        ),
        onChangeAccountName = {},
        onChangeAccountStatus = {},
        onChangeAccountValue = {},
        onSave = {}
    )
}