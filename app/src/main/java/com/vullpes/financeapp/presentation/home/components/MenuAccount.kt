package com.vullpes.financeapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.R


@Composable
fun MenuAccount(
    onDeposit:() ->Unit,
    onWithdraw:() -> Unit,
    onTransference:() ->Unit
) {

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
            Icon(painterResource(id = R.drawable.deposit),modifier = Modifier.size(20.dp), contentDescription = null)
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
            Icon(painterResource(id = R.drawable.withdraw),modifier = Modifier.size(20.dp), contentDescription = null)
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
            Icon(painterResource(id = R.drawable.transference),modifier = Modifier.size(20.dp), contentDescription = null)
            Spacer(Modifier.height(4.dp))
            Text(text = "Transference", style = MaterialTheme.typography.bodyMedium)
        }
    }


}

@Preview(showBackground = true)
@Composable
fun MenuAccountPrev() {
    MenuAccount({},{},{})
}


