package com.vullpes.common.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.vullpes.common.R


@Composable
fun MessageDialog(
    title:String = "Titulo",
    message:String,
    informative: Boolean =false,
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {


    Dialog(onDismissRequest = { onDismiss() }) {

        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )) {

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = message,
                textAlign = TextAlign.Justify,
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceAround) {

                if(informative){
                    TextButton(onClick = {
                        onNegativeClick()
                    }) {

                        Text(
                            stringResource(R.string.no),
                            fontWeight = FontWeight.Bold,
                            color = if(isSystemInDarkTheme()) Color.White else Color.Black,
                            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                        )
                    }
                }

                TextButton(onClick = {
                    onPositiveClick()
                }) {
                    Text(
                        stringResource(R.string.ok),
                        fontWeight = FontWeight.ExtraBold,
                        color = if(isSystemInDarkTheme())  Color.White else Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }
            }
        }

    }
}