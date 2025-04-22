package com.lincollincol.expensestracker.feature.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lincollincol.expensestracker.core.common.DF_PATTERN_BTC_PREVIEW
import com.lincollincol.expensestracker.core.model.Currency
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.component.ETButton
import com.lincollincol.expensestracker.core.ui.component.AmountInput
import com.lincollincol.expensestracker.core.ui.component.SectionHeading
import com.lincollincol.expensestracker.core.ui.extensions.iconRes
import com.lincollincol.expensestracker.core.ui.extensions.nameRes
import com.lincollincol.expensestracker.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.expensestracker.core.ui.theme.DarkRed
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme

@Composable
internal fun TransactionRoute(
    viewModel: TransactionViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val transactionUiState by viewModel.transactionUiState.collectAsStateWithLifecycle()
    val categoriesUiState by viewModel.categoriesUiState.collectAsStateWithLifecycle()
    val inputState by viewModel.inputUiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.backEvent.collect { onBackClick() }
    }
    TransactionScreen(
        input = inputState,
        transactionUiState = transactionUiState,
        categoriesUiState = categoriesUiState,
        onExpenseValueChange = viewModel::updateInput,
        onCategoryClick = viewModel::selectCategory,
        onSaveClick = viewModel::makeTransaction,
        onCancelClick = onBackClick
    )
}

@Composable
internal fun TransactionScreen(
    input: String?,
    transactionUiState: TransactionUiState,
    categoriesUiState: List<CategoryItemUiState>,
    onExpenseValueChange: (String) -> Unit,
    onCategoryClick: (Transaction.Category) -> Unit,
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
            inputSection(
                input = input,
                transactionUiState = transactionUiState,
                onExpenseValueChange = onExpenseValueChange
            )
            categorySection(
                categoriesUiState = categoriesUiState,
                onCategoryClick = onCategoryClick
            )
        }

        ETButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Add",
            onClick = onSaveClick,
            enabled = transactionUiState.isValid
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

private fun LazyListScope.inputSection(
    input: String?,
    transactionUiState: TransactionUiState,
    onExpenseValueChange: (String) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Amount",
            style = MaterialTheme.typography.headlineSmall
        )
        AmountInput(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            value = input.orEmpty(),
            hint = "0.0",
            suffix = Currency.BTC.name,
            onValueChange = onExpenseValueChange
        )
    }
    item {
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
        ) {
            val formatter = rememberCurrencyValueFormatter(pattern = DF_PATTERN_BTC_PREVIEW)
            Text(
                text = "Balance: ",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = formatter.format(transactionUiState.balance),
                color = if (transactionUiState.balance >= 0) MaterialTheme.colorScheme.onBackground else DarkRed
            )
        }
    }
}

private fun LazyListScope.categorySection(
    categoriesUiState: List<CategoryItemUiState>,
    onCategoryClick: (Transaction.Category) -> Unit
) {
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
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categoriesUiState.onEach {
                CategoryItem(
                    categoryUiState = it,
                    onClick = onCategoryClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun TransactionScreenPreview() {
    ExpensesTrackerTheme {
        TransactionScreen(
            input = null,
            transactionUiState = TransactionUiState.Empty,
            categoriesUiState = emptyList(),
            onExpenseValueChange = {},
            onCategoryClick = {},
            onSaveClick = {},
            onCancelClick = {}
        )
    }
}