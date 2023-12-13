package com.vullpes.financeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallMade
import androidx.compose.material.icons.filled.CallReceived
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.R
import com.vullpes.financeapp.domain.model.Transaction
import com.vullpes.financeapp.domain.util.CurrencyAmountInputVisualTransformation
import com.vullpes.financeapp.domain.util.tolongStringDate
import com.vullpes.financeapp.domain.util.toCurrencyFormat
import java.util.Date


@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {


            Image(
                when {
                    transaction.withdrawal -> Icons.Filled.CallMade
                    transaction.deposit -> Icons.Filled.CallReceived
                    transaction.transference -> Icons.Filled.CallMade
                    else -> Icons.Filled.CallMade
                },
                modifier = Modifier
                    .padding(6.dp),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.profile_image)
            )

            Column {
                Text(
                    text = transaction.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                transaction.categoryName?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Text(text = transaction.dateTransaction.tolongStringDate())
            }
        }


        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = when {
                        transaction.withdrawal -> Color.Red
                        transaction.deposit -> Color.Green
                        transaction.transference -> Color.Red
                        else -> Color.Transparent
                    }
                ).padding(6.dp),
            text = "$${transaction.value.toCurrencyFormat()}",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = Color.White)
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PrevTransactions() {
    TransactionItem(
        transaction = Transaction(
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
        )
    )


}