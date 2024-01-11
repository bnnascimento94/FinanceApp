package com.vullpes.category.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vullpes.category.Category
import java.util.Date

@Composable
fun CategoryItem(
    category: Category,
    onCategory:(Category) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp).clickable { onCategory(category) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(category.nameCategory?:"", style = MaterialTheme.typography.titleMedium)

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    color = if(category.active) Green else Color.Red
                ).padding(6.dp),
            text = if(category.active) "Active" else "Disabled",
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = Color.White)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun PrevTransactions() {
    CategoryItem(
       category = Category(categoryID = 1, nameCategory = "Categoria 1", active = false, dataCreation = Date()),
        onCategory = {}
    )


}