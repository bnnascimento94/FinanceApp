package com.vullpes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.category.Category
import java.util.Date
import com.vullpes.util.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetCategory(
    buttonSaveEnabled: Boolean,
    category: Category,
    onChangeCategoryName: (String) -> Unit,
    onChangeCategoryStatus: (Boolean) -> Unit,
    onSave: (SheetState) -> Unit,
    onDismiss: (SheetState) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss(modalBottomSheetState) },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },

        ) {
        CreateCategory(
            buttonSaveEnabled= buttonSaveEnabled,
            category = category,
            onChangeCategoryName = onChangeCategoryName,
            onChangeCategoryStatus = onChangeCategoryStatus,
            onSave = { onSave(modalBottomSheetState) }
        )
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCategory(
    buttonSaveEnabled: Boolean,
    category: Category,
    onChangeCategoryName: (String) -> Unit,
    onChangeCategoryStatus: (Boolean) -> Unit,
    onSave: () -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(R.string.transaction_category),
            style = MaterialTheme.typography.titleLarge
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(text = stringResource(R.string.category_active))
            Switch(
                checked = category.active,
                onCheckedChange = onChangeCategoryStatus
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            label = { Text(stringResource(R.string.category_name)) },
            value = category.nameCategory?:"",
            onValueChange = onChangeCategoryName,
        )

        Button(
            enabled = buttonSaveEnabled,
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
fun PreviewCreateCategory() {
    CreateCategory(
        buttonSaveEnabled = true,
        category = Category(
            categoryID = 1,
            nameCategory = "Despesas de Casa",
            active = true,
            dataCreation = Date()
        ),
        onChangeCategoryName = {},
        onChangeCategoryStatus = {},
        onSave = {}
    )
}