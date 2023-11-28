package com.vullpes.financeapp.presentation.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.financeapp.R
import com.vullpes.financeapp.presentation.home.components.TopAppBar

@Composable
fun ProfileTopAppBar(
    onBackScreen: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = { onBackScreen() }, modifier = Modifier.padding(6.dp)) {
            Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back_screen))
        }
        Text(text = "Profile", style = MaterialTheme.typography.titleLarge)
    }
}


@Preview(showBackground = true)
@Composable
fun PrevTopBar() {
    TopAppBar(onMenuClick = {}, onProfileClick = {})
}