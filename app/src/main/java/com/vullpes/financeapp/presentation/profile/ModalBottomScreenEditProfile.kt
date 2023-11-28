package com.vullpes.financeapp.presentation.profile

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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.domain.util.CurrencyAmountInputVisualTransformation
import com.vullpes.financeapp.presentation.components.DropDownMenu
import com.vullpes.financeapp.presentation.components.Transaction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomScreenEditProfile(
    onDismiss: (SheetState) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss(modalBottomSheetState) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Transaction()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Transaction() {

    var selectedOption by remember { mutableStateOf("Option1") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)) {
        Text(
            text = "Transaction",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(modifier = Modifier.padding(6.dp),text = "Kind of Transaction")
        Row(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedOption == "Withdraw", onClick = {
                    selectedOption = "Withdraw"
                }
                )
                Text(text= "Withdraw")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedOption == "Deposit", onClick = {
                    selectedOption = "Deposit"
                })
                Text(text= "Deposit")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selectedOption == "Transference", onClick = {
                    selectedOption = "Transference"
                })
                Text(text= "Transference")
            }
        }


        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text("Transaction Name") },
            value = "",
            onValueChange = { },
        )

        DropDownMenu(modifier = Modifier.padding(8.dp), label = "Category", onItemSelected = {})

        if(selectedOption == "Transference"){
            DropDownMenu(modifier = Modifier.padding(8.dp), label = "Account", onItemSelected = {})
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(text = "Value") },
            value = "",
            onValueChange = {

            },
            prefix = { Text(text = "$") },
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )

        Button(modifier = Modifier.fillMaxWidth().padding(8.dp),onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }


    }

}