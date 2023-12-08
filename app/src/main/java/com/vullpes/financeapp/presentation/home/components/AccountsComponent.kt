package com.vullpes.financeapp.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vullpes.financeapp.R
import com.vullpes.financeapp.domain.model.Account
import com.vullpes.financeapp.domain.util.toCurrencyFormat
import com.vullpes.financeapp.ui.theme.Purple40
import java.util.Date

@Composable
fun AccountsComponent(
    accounts: List<Account>,
    onAddAccount: () -> Unit,
    onAccountSelected: (Int) -> Unit
) {

    LazyRow(modifier = Modifier.fillMaxWidth()) {
        item {
            AddAccount(onAddAccount = onAddAccount)
        }
        items(accounts) { account ->
            ItemAccount(account = account, onAccountSelected = onAccountSelected)
        }
    }

}

@Composable
fun AddAccount(onAddAccount: () -> Unit) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Column(
        modifier = Modifier
            .padding(6.dp)
            .width(100.dp)
            .height(200.dp)
            .drawBehind {
                drawRoundRect(
                    color = Color.Red,
                    style = stroke,
                    cornerRadius = CornerRadius(x = 6.dp.toPx(), y = 6.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(6.dp))
            .background(color = MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = { onAddAccount() }) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_new_account))
        }
    }
}

@Composable
fun ItemAccount(account: Account, onAccountSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .width(250.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Purple40).clickable { onAccountSelected(account.accountID) },
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var moneyVisibility = remember{ mutableStateOf(false) }
        Column(modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()) {
            Text(
                text = account.accountName?:"",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(6.dp),
                color = Color.White
            )

            Surface (modifier = Modifier.padding(6.dp),shape = RoundedCornerShape(30.dp), color = if(account.activeAccount) Color.Blue else Color.Red){
                Text(
                    text = if(account.activeAccount) "Ativo" else "Inativo",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(6.dp),
                    color = Color.White
                )
            }


        }


        Row(verticalAlignment = Alignment.CenterVertically){
            Text(text = if(moneyVisibility.value) "$${account.accountBalance?.toCurrencyFormat()}" else "$ ******" ,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(6.dp),
                color = Color.White
            )
            IconButton(onClick = { moneyVisibility.value = !moneyVisibility.value }) {
                Icon(
                    imageVector = if(moneyVisibility.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Show Password",
                    tint = Color.White
                )
            }
        }

    }
}


@Preview()
@Composable
fun AccountPrev() {
    AccountsComponent(accounts = listOf(
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
    ),{},{})
}

