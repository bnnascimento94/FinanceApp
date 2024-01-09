package com.vullpes.charts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.util.R

@Composable
fun ChartTopAppBar(
    onBackPressed:() ->Unit,
    openCalendarDialog:() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onBackPressed() }, modifier = Modifier.padding(6.dp)) {
            Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back_screen))
        }
        Text(text = "Chart", style = MaterialTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconButton(
            onClick = { openCalendarDialog() }, modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
        ) {
            Icon(
                Icons.Default.CalendarMonth,
                contentDescription = stringResource(R.string.calendar)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevTopBar() {
    ChartTopAppBar(onBackPressed = {}, openCalendarDialog = {})
}