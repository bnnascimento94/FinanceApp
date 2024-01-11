package com.vullpes.financeapp.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
import com.vullpes.account.domain.Account
import com.vullpes.authentication.User

@Composable
fun TopAppBar(
    account: com.vullpes.account.domain.Account? = null,
    user: com.vullpes.authentication.User?,
    onProfileClick: () -> Unit,
    onMenuClick: () -> Unit,
    onEditAccount:(com.vullpes.account.domain.Account) -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IconButton(onClick = { onMenuClick() }, modifier = Modifier.padding(6.dp)) {
            Icon(Icons.Default.Menu, contentDescription = stringResource(R.string.application_menu))
        }
        Text(
            text = account?.accountName ?: stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row{
            account?.let {account ->
                IconButton(onClick = { onEditAccount(account)}) {
                    Icon(Icons.Default.Edit, contentDescription = stringResource(R.string.edit_account))
                }
            }
            user?.imgSrc?.let {
                AsyncImage(
                    model = it,
                    placeholder = painterResource(R.drawable.no_user),
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .size(30.dp)
                        .clickable{onProfileClick()},

                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.profile_image)
                )
            }?: kotlin.run {
                Image(
                    painterResource(id = R.drawable.no_user),
                    modifier = Modifier
                        .padding(6.dp)
                        .clip(CircleShape)
                        .size(30.dp)
                        .clickable{onProfileClick()},
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(R.string.profile_image)
                )
            }


        }

    }
}


@Preview(showBackground = true)
@Composable
fun PrevTopBar() {
    TopAppBar(
        onMenuClick = {},
        user = com.vullpes.authentication.User(
            id = 1,
            name = "",
            email = "",
            password = "",
            imgSrc = ""
        ),
        onProfileClick = {}, onEditAccount = {}
    )
}