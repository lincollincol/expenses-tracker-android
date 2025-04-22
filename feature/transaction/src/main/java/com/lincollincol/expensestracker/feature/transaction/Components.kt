package com.lincollincol.expensestracker.feature.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.extensions.iconRes
import com.lincollincol.expensestracker.core.ui.extensions.nameRes
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    categoryUiState: CategoryItemUiState,
    onClick: (Transaction.Category) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick(categoryUiState.category) }
            .padding(12.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val imageColor = with (MaterialTheme.colorScheme) {
            if (categoryUiState.isSelected) background else onBackground
        }
        val imageContainerColor = with (MaterialTheme.colorScheme) {
            if (categoryUiState.isSelected) primary else background
        }
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(imageContainerColor),
            painter = painterResource(categoryUiState.category.iconRes),
            contentDescription = stringResource(categoryUiState.category.nameRes),
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(imageColor)
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(categoryUiState.category.nameRes),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun CategoryItemPreview() {
    ExpensesTrackerTheme {
        CategoryItem(
            categoryUiState = CategoryItemUiState(Transaction.Category.GROCERIES, false),
            onClick = {}
        )
    }
}