package com.vullpes.financeapp.presentation.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.presentation.components.EmptyPage
import com.vullpes.financeapp.presentation.components.TransactionItem
import com.vullpes.financeapp.util.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    uiState: UiTransactionsState,
    onBackScreen:() -> Unit,
    onSearchItem: () -> Unit,
    onActiveSearchChange: (Boolean) -> Unit,
    onSearchTextChange: (String) -> Unit,
) {

    Column(modifier = Modifier
        .fillMaxSize()) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(
                    onClick = { onBackScreen() }, modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back Screen")
                }

            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(if (uiState.searchBarActive) 0.dp else 6.dp)
                    .weight(1f),
                query = uiState.textPesquisa,
                onQueryChange = { onSearchTextChange(it) },
                onSearch = { onSearchItem() },
                active = uiState.searchBarActive,
                onActiveChange = { status ->
                    onActiveSearchChange(status)
                },
                placeholder = {
                    Text(text = "Search Transactions", overflow = TextOverflow.Ellipsis)
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
                                if (uiState.textPesquisa.isNotEmpty()) {
                                    onSearchTextChange("")
                                } else {
                                    onActiveSearchChange(false)
                                }

                            }
                        )
                    }

                }
            ) {}
        }

        when (uiState.transactions) {
            is Resource.Error -> {
                uiState.transactions.message?.let {
                    EmptyPage(
                        title = "Error",
                        subtitle = it
                    )
                }
            }

            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Success -> {
                uiState.transactions.data?.let {transactions ->
                    if (transactions.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(transactions){ transaction ->
                                TransactionItem(transaction = transaction)
                            }
                        }
                    } else {
                        EmptyPage(title = "Transactions", subtitle = "No Content")
                    }
                }

            }
        }

    }
}