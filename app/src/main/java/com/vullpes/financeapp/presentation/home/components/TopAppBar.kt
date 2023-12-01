package com.vullpes.financeapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vullpes.financeapp.R

@Composable
fun TopAppBar(
    accountName:String? = null,
    onProfileClick:() ->Unit,
    onMenuClick:() ->Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = { onMenuClick() }, modifier = Modifier.padding(6.dp)) {
            Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.application_menu))
        }
        Text(text = accountName?:"Finance App", style = MaterialTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
        AsyncImage(
            model = "",
            placeholder = painterResource(R.drawable.no_user),
            modifier = Modifier
                .padding(6.dp)
                .clip(CircleShape)
                .size(30.dp)
                .clickable { onProfileClick() },
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.profile_image)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PrevTopBar() {
    TopAppBar(onMenuClick = {}, onProfileClick = {})
}