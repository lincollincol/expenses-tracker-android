package com.lincollincol.feature.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lincollincol.core.ui.component.ETButton
import com.lincollincol.core.ui.component.NumberInput
import com.lincollincol.core.ui.component.SectionHeading
import com.lincollincol.core.ui.theme.ExpensesTrackerTheme

@Composable
internal fun TransactionRoute(
    onBackClick: () -> Unit
) {
    TransactionScreen(
        onExpenseValueChange = {},
        onSaveClick = {},
        onCancelClick = onBackClick
    )
}

@Composable
internal fun TransactionScreen(

    onExpenseValueChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
    ) {
        SectionHeading(text = "New Transaction")

        LazyColumn(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .weight(1F),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            inputSection(onExpenseValueChange = {})
            categorySection()
        }

        ETButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Add",
            onClick = onSaveClick
        )
        ETButton(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 24.dp)
                .fillMaxWidth(),
            text = "Cancel",
            onClick = onCancelClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        )
    }
}

@Composable
fun CategoryItem(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background),
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "",
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "Food",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

private fun LazyListScope.inputSection(
    onExpenseValueChange: (String) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Amount",
            style = MaterialTheme.typography.headlineSmall
        )
        NumberInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            value = "",
            suffix = "USD",
            onValueChange = onExpenseValueChange
        )
    }
}

private fun LazyListScope.categorySection() {
    item {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Category",
            style = MaterialTheme.typography.headlineSmall
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
            CategoryItem()
        }
    }
}

@Preview
@Composable
private fun TransactionScreenPreview() {
    ExpensesTrackerTheme {
        TransactionScreen(
            onExpenseValueChange = {},
            onSaveClick = {},
            onCancelClick = {}
        )
    }
}