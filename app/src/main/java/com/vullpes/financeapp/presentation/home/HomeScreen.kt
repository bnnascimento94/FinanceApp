package com.vullpes.financeapp.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.R
import com.vullpes.financeapp.account.domain.Account
import com.vullpes.financeapp.transaction.domain.Transaction
import com.vullpes.components.EmptyPage
import com.vullpes.components.TransactionItem
import com.vullpes.financeapp.presentation.home.components.AccountsComponent
import com.vullpes.financeapp.presentation.home.components.TopAppBar
import com.vullpes.financeapp.ui.theme.Purple40
import java.util.Date


@Composable
fun HomeScreen(
    uiState: UiStateHome,
    drawerState: DrawerState,
    onProfileClick: () -> Unit,
    onMenuClick: () -> Unit,
    onCreateAccount: () -> Unit,
    onAccountSelected: (Int) -> Unit,
    onDeposit: (Int) -> Unit,
    onWithdraw: (Int) -> Unit,
    onTransference: (Int) -> Unit,
    onChart: (Int) -> Unit,
    onCategoryClicked: () -> Unit,
    onExitAppClicked: () -> Unit,
    onEditAccount: (Account) -> Unit,
    allTransactions:(Int) -> Unit,
) {




    NavigationDrawer(
        drawerState = drawerState,
        onCategoryClicked = onCategoryClicked,
        onExitAppClicked = onExitAppClicked
    ) {
        Scaffold(modifier = Modifier
            .fillMaxSize(),
            topBar = {
                TopAppBar(
                    account = uiState.accountSelected,
                    user = uiState.user,
                    onMenuClick = onMenuClick,
                    onProfileClick = onProfileClick,
                    onEditAccount = onEditAccount
                )
            }
        ) { padingValues ->


            LazyColumn(modifier = Modifier
                .padding(padingValues)
                .padding(6.dp)){

                item {
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "My Accounts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AccountsComponent(
                        accounts = uiState.accounts,
                        onAddAccount = onCreateAccount,
                        onAccountSelected = onAccountSelected
                    )
                }
                item {
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
                                .clickable{
                                    uiState.accountSelected?.accountID?.let {
                                        onDeposit(it)
                                    }
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
                                .clickable{
                                        uiState.accountSelected?.accountID?.let {
                                            onWithdraw(it)
                                        }
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
                                .clickable{
                                        uiState.accountSelected?.accountID?.let {
                                            onTransference(it)
                                        }
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
                                .clickable{
                                        uiState.accountSelected?.accountID?.let {
                                            onChart(it)
                                        }
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
                }
                item {
                    Row(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Last transactions",
                            style = MaterialTheme.typography.titleMedium
                        )

                        TextButton(onClick = { uiState.accountSelected?.accountID?.let { allTransactions(it) }  }) {
                            Text(text = "List All", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }

                if (uiState.transactions.isNotEmpty()) {

                    items(uiState.transactions) {
                        com.vullpes.components.TransactionItem(transaction = it)
                    }

                } else {
                    item{
                        com.vullpes.components.EmptyPage(
                            title = stringResource(R.string.transactions),
                            subtitle = stringResource(R.string.there_are_no_records)
                        )
                    }
                }


            }


        }
    }
}


@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    onCategoryClicked: () -> Unit,
    onExitAppClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                       // modifier = Modifier.size(250.dp),
                        painter = painterResource(id = R.drawable.deposit),
                        contentDescription = "Logo Image"
                    )
                }
                NavigationDrawerItem(
                    label = {
                        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                            Icon(
                                Icons.Default.Category,
                                contentDescription = stringResource(R.string.transaction_category)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = stringResource(R.string.transaction_category),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    selected = false,
                    onClick = onCategoryClicked
                )
                NavigationDrawerItem(
                    label = {
                        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
                            Icon(
                                Icons.Default.ExitToApp,
                                contentDescription = stringResource(R.string.logout_icon)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = stringResource(R.string.logout),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    },
                    selected = false,
                    onClick = onExitAppClicked
                )

            }
        },
        content = content
    )

}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true, showSystemUi = false
)
@Composable
fun prevHomeScreeNight() {

    /**

     */

    HomeScreen(
        uiState = UiStateHome(
            accountSelected = Account(
                1,
                "Casa",
                accountBalance = 1500.00,
                activeAccount = true,
                dataCreationAccount = Date()
            ),
            accounts = listOf(
                Account(
                    1,
                    "Casa",
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
            )
        ),
        drawerState = DrawerState(initialValue = DrawerValue.Closed),
        onProfileClick = { },
        onMenuClick = { },
        onCreateAccount = { },
        onAccountSelected = {},
        onDeposit = { },
        onWithdraw = { },
        onCategoryClicked = {},
        onTransference = {},
        onChart = {},
        onExitAppClicked = {},
        onEditAccount = {},
        allTransactions = {}
    )
}