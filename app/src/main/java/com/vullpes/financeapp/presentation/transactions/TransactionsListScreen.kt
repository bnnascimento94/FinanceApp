package com.vullpes.financeapp.presentation.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.vullpes.financeapp.R
import com.vullpes.financeapp.presentation.components.EmptyPage
import com.vullpes.financeapp.presentation.components.TransactionItem
import com.vullpes.financeapp.util.Resource
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsListScreen(
    uiState: UiTransactionsListState,
    onBackScreen: () -> Unit,
    onSearchItem: () -> Unit,
    onActiveSearchChange: (Boolean) -> Unit,
    onSearchTextChange: (String) -> Unit,
    onSelectedDate: (LocalDate) -> Unit
) {

    val dateDialog = rememberSheetState()


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            if(!uiState.searchBarActive){
                IconButton(
                    onClick = { onBackScreen() }, modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_screen)
                    )
                }
            }


            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if (uiState.searchBarActive) 0.dp else 6.dp)
                    .weight(1f),
                query = uiState.textSearch,
                onQueryChange = { onSearchTextChange(it) },
                onSearch = { onSearchItem() },
                active = uiState.searchBarActive,
                onActiveChange = { status ->
                    onActiveSearchChange(status)
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_transactions),
                        overflow = TextOverflow.Ellipsis
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (uiState.searchBarActive) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Icon",
                            modifier = Modifier.clickable {
                                if (uiState.textSearch.isNotEmpty()) {
                                    onSearchTextChange("")
                                } else {
                                    onActiveSearchChange(false)
                                }

                            }
                        )
                    }

                }
            ) {}

            IconButton(
                onClick = { dateDialog.show() }, modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = stringResource(R.string.calendar)
                )
            }
        }

        if (uiState.transactions.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(uiState.transactions) { transaction ->
                    TransactionItem(transaction = transaction)
                }
            }
        } else {
            EmptyPage(title = "Transactions", subtitle = "No Content")
        }





    }

    CalendarDialog(
        state = dateDialog,
        selection = CalendarSelection.Date { localDate -> onSelectedDate(localDate) },
        config = CalendarConfig(monthSelection = true, yearSelection = true)
    )
}

@Preview
@Composable
fun PreviewTransactionScreen() {
    TransactionsListScreen(
        uiState = UiTransactionsListState(),
        onBackScreen = { /*TODO*/ },
        onSearchItem = { /*TODO*/ },
        onActiveSearchChange = {},
        onSearchTextChange = {},
        onSelectedDate = {}
    )
}