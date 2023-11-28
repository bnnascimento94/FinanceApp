package com.vullpes.financeapp.presentation.components


import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    itemSelected:String = "",
    label: String,
    listItems: List<String> = arrayListOf(),
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    editableText: Boolean = false
) {


    var expanded by remember { mutableStateOf(false) }

    // remember the selected item
    var selectedItem by remember {

        if (itemSelected.isNotBlank()) mutableStateOf(itemSelected) else mutableStateOf(
            ""
        )
    }

    LaunchedEffect(key1 = (itemSelected != selectedItem)){
        Log.e("recomposition", "item lista: $itemSelected / itemselecionado: $selectedItem")
        if(itemSelected.isNotBlank()){
            selectedItem = itemSelected
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
        modifier = modifier
    ) {

        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedItem,
            onValueChange = { if (editableText) selectedItem = it},
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },

        )

        if (editableText) {

            val filteringOptions =
                listItems.filter { it.contains(selectedItem, ignoreCase = true) }

            if (filteringOptions.isNotEmpty()) {
                // menu
                DropdownMenu(
                    modifier =Modifier.exposedDropdownSize(),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    filteringOptions.forEach { selectionOption ->
                        // menu item
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(selectionOption)
                                selectedItem = selectionOption
                                expanded = false

                            },
                            text = { Text(text = selectionOption) },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            } else {

                // menu
                DropdownMenu(
                    modifier =Modifier.exposedDropdownSize(),
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    // this is a column scope
                    // all the items are added vertically
                    listItems.forEach { selectionOption ->
                        // menu item
                        DropdownMenuItem(
                            onClick = {
                                onItemSelected(selectionOption)
                                selectedItem = selectionOption
                                expanded = false

                            },
                            text = { Text(text = selectionOption) },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }
            }
        }else{
            DropdownMenu(
                modifier =Modifier.exposedDropdownSize(),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                listItems.forEach { selectionOption ->
                    // menu item
                    DropdownMenuItem(
                        onClick = {
                            onItemSelected(selectionOption)
                            selectedItem = selectionOption
                            expanded = false

                        },
                        text = { Text(text = selectionOption) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }


        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDropDownItem() {
    DropDownMenu(
        label = "Teste",
        listItems = arrayListOf("Teste 1", "Teste 2","Teste 3","Teste 4"),
        onItemSelected = {})
}