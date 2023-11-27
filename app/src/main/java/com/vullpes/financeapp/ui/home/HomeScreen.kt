package com.vullpes.financeapp.ui.home

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.R
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.ui.home.components.AccountsComponent
import com.vullpes.financeapp.ui.home.components.TopAppBar
import com.vullpes.financeapp.ui.home.components.TransactionItem
import com.vullpes.financeapp.ui.theme.Purple40
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    accounts: List<Account>,
    transactions: List<Transaction>,
    onProfileClick: () -> Unit,
    onMenuClick: () -> Unit,
    onAddAccount: () -> Unit,
    onAccountSelected: (Int) -> Unit,
    onDeposit: () -> Unit,
    onWithdraw: () -> Unit,
    onTransference: () -> Unit
) {

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(onMenuClick = onMenuClick, onProfileClick = onProfileClick)
        }
    ) { padingValues ->

        Column(
            modifier = Modifier
                .padding(padingValues)
                .padding(6.dp)
        ) {
            Text(modifier = Modifier.padding(start= 6.dp),text = "My Accounts", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(10.dp))
            AccountsComponent(
                accounts = accounts,
                onAddAccount = onAddAccount,
                onAccountSelected = onAccountSelected
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onDeposit()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.deposit),
                        modifier = Modifier.size(30.dp),
                        tint = Purple40,
                        contentDescription = null
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = "Deposit", style = MaterialTheme.typography.bodyMedium)
                }


                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onWithdraw()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.withdraw),
                        modifier = Modifier.size(30.dp),
                        tint = Purple40,
                        contentDescription = null
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = "Withdraw", style = MaterialTheme.typography.bodyMedium)
                }


                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onTransference()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.transference),
                        modifier = Modifier.size(30.dp),
                        tint = Purple40,
                        contentDescription = null
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = "Transference", style = MaterialTheme.typography.bodyMedium)
                }

                Column(
                    modifier = Modifier
                        .padding(6.dp)
                        .clickable {
                            onTransference()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painterResource(id = R.drawable.chart),
                        modifier = Modifier.size(30.dp),
                        tint = Purple40,
                        contentDescription = null
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = "Chart", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Column(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "Last transactions",style = MaterialTheme.typography.titleMedium)

                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "List All",style = MaterialTheme.typography.titleMedium)
                    }
                }


                LazyColumn() {
                    items(transactions) {
                        TransactionItem(transaction = it)
                    }
                }

            }
        }

    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun prevHomeScreen() {
    HomeScreen(
        accounts = listOf(
            Account(
                1,
                "Minha conta pessoal",
                accountBalance = 1500.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            Account(
                2,
                "Minha conta pessoal",
                accountBalance = 1400.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            Account(
                3,
                "Minha conta pessoal",
                accountBalance = 1300.00,
                activeAccount = false,
                dataCreationAccount = Date()
            )
        ),
        transactions = listOf(
            Transaction(
                transactionID = 1,
                categoryID = 1,
                name = "Transaction 1",
                categoryName = "Scholarchip",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = false,
                withdrawal = false,
                transference = true,
                value = 150.00,
                dateTransaction = Date()
            ),
            Transaction(
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
            Transaction(
                transactionID = 1,
                name = "Transaction 3",
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
            Transaction(
                transactionID = 1,
                name = "Transaction 4",
                categoryID = 1,
                categoryName = "Deposit",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = true,
                withdrawal = false,
                transference = false,
                value = 150.00,
                dateTransaction = Date()
            ),
            Transaction(
                transactionID = 1,
                name = "Transaction 5",
                categoryID = 1,
                categoryName = "Deposit",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = false,
                withdrawal = false,
                transference = true,
                value = 150.00,
                dateTransaction = Date()
            ),
            Transaction(
                transactionID = 1,
                name = "Transaction 6",
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
            Transaction(
                transactionID = 1,
                name = "Transaction 7",
                categoryID = 1,
                categoryName = "Deposit",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = true,
                withdrawal = false,
                transference = false,
                value = 150.00,
                dateTransaction = Date()
            ),
        ),
        onProfileClick = {  },
        onMenuClick = { },
        onAddAccount = { },
        onAccountSelected = {},
        onDeposit = {  },
        onWithdraw = {  }) {

    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true, showSystemUi = false
)
@Composable
fun prevHomeScreeNight() {
    HomeScreen(
        accounts = listOf(
            Account(
                1,
                "Minha conta pessoal",
                accountBalance = 1500.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            Account(
                2,
                "Minha conta pessoal",
                accountBalance = 1400.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            Account(
                3,
                "Minha conta pessoal",
                accountBalance = 1300.00,
                activeAccount = false,
                dataCreationAccount = Date()
            )
        ),
        transactions = listOf(
            Transaction(
                transactionID = 1,
                categoryID = 1,
                name = "Transaction 1",
                categoryName = "Scholarchip",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = false,
                withdrawal = false,
                transference = true,
                value = 150.00,
                dateTransaction = Date()
            ),
            Transaction(
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
            Transaction(
                transactionID = 1,
                name = "Transaction 3",
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
            Transaction(
                transactionID = 1,
                name = "Transaction 4",
                categoryID = 1,
                categoryName = "Deposit",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = true,
                withdrawal = false,
                transference = false,
                value = 150.00,
                dateTransaction = Date()
            ),
            Transaction(
                transactionID = 1,
                name = "Transaction 5",
                categoryID = 1,
                categoryName = "Deposit",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = false,
                withdrawal = false,
                transference = true,
                value = 150.00,
                dateTransaction = Date()
            ),
            Transaction(
                transactionID = 1,
                name = "Transaction 6",
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
            Transaction(
                transactionID = 1,
                name = "Transaction 7",
                categoryID = 1,
                categoryName = "Deposit",
                accountFromID = 1,
                accountFromName = "Personal Account",
                deposit = true,
                withdrawal = false,
                transference = false,
                value = 150.00,
                dateTransaction = Date()
            ),
        ),
        onProfileClick = {  },
        onMenuClick = { },
        onAddAccount = { },
        onAccountSelected = {},
        onDeposit = {  },
        onWithdraw = {  }) {

    }
}