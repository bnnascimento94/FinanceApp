package com.vullpes.category.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vullpes.common.R

@Composable
fun CategoryTopBar(
    onBackPressed:() -> Unit,
    openCategoryModal:() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { onBackPressed() }, modifier = Modifier.padding(6.dp)) {
            Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back_screen))
        }
        Text(text = "Categories", style = MaterialTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
        IconButton(onClick = { openCategoryModal() }, modifier = Modifier.padding(6.dp)) {
            Icon(Icons.Default.Add, contentDescription = stringResource(R.string.back_screen))
        }
    }
}