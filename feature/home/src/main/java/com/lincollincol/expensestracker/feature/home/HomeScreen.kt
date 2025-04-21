package com.lincollincol.expensestracker.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.component.SectionHeading
import com.lincollincol.expensestracker.core.ui.extensions.formattedExpense
import com.lincollincol.expensestracker.core.ui.extensions.iconRes
import com.lincollincol.expensestracker.core.ui.extensions.nameRes
import com.lincollincol.expensestracker.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.expensestracker.core.ui.theme.DarkGreen
import com.lincollincol.expensestracker.core.ui.theme.DarkRed
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    val homeUiState by viewModel.balanceUiState.collectAsStateWithLifecycle()
    val depositUiState by viewModel.depositUiState.collectAsStateWithLifecycle()
    val transactionsUiItems = viewModel.transactionsUiState.collectAsLazyPagingItems()

    HomeScreen(
        balanceUiState = homeUiState,
        depositUiState = depositUiState,
        transactionsUiItems = transactionsUiItems,
//        onAddTransactionClick = onAddTransactionClick,
        onAddTransactionClick = { viewModel.maket() },

        // Screen-level events
        onDepositClick = viewModel::depositToBalance,
        onDepositValueChange = viewModel::updateDepositValue,
        onDepositSaveClick = viewModel::saveDepositValue,
        onDepositCloseClick = viewModel::cancelDepositToBalance
    )
}

@Composable
internal fun HomeScreen(
    balanceUiState: BalanceUiState,
    depositUiState: DepositUiState,
    transactionsUiItems: LazyPagingItems<TransactionUiState>,
    onDepositClick: () -> Unit,
    onAddTransactionClick: () -> Unit,
    onDepositValueChange: (String) -> Unit,
    onDepositSaveClick: () -> Unit,
    onDepositCloseClick: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
    ) {
        balanceSection(
            balanceBtc = balanceUiState.balanceBtc,
            balanceUsd = balanceUiState.balanceUsd,
            exchangeRateBtcUsd = balanceUiState.exchangeRateBtcUsd,
            exchangeRateChangePercent = balanceUiState.exchangeRateChangePercent,
            onAddFundsClick = onDepositClick,
            onAddTransactionClick = onAddTransactionClick
        )
        expensesSection(transactionsUiItems)
    }
    if (depositUiState.isVisible) {
        DepositDialog(
            input = depositUiState.input,
            equivalent = depositUiState.equivalent,
            inputCurrency = depositUiState.inputCurrency.name,
            equivalentCurrency = depositUiState.equivalentCurrency.name,
            onDepositValueChange = onDepositValueChange,
            onDepositSaveClick = onDepositSaveClick,
            onDismiss = onDepositCloseClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.balanceSection(
    balanceBtc: Float,
    balanceUsd: Float?,
    exchangeRateBtcUsd: Float?,
    exchangeRateChangePercent: Float?,
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    stickyHeader {
        SectionHeading(text = "Balance") {
            val formatter = rememberCurrencyValueFormatter()
            if (exchangeRateBtcUsd != null && exchangeRateChangePercent != null) {
                val percentColor = if (exchangeRateChangePercent >= 0) DarkGreen else DarkRed
                Text(
                    modifier = Modifier.weight(0.5F),
                    text = buildAnnotatedString {
                        append("${formatter.format(exchangeRateBtcUsd)} USD/BTC")
                        withStyle(style = SpanStyle(color = percentColor)) {
                            append(" (${formatter.format(exchangeRateChangePercent)}%)")
                        }
                    },
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }
        }
    }
    item {
        BalanceBanner(
            modifier = Modifier.padding(top = 24.dp),
            balanceBtc = balanceBtc,
            balanceUsd = balanceUsd,
            onAddFundsClick = onAddFundsClick,
            onAddTransactionClick = onAddTransactionClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.expensesSection(
    transactionsUiItems: LazyPagingItems<TransactionUiState>,
) {
    stickyHeader {
        SectionHeading(text = "Operations")
    }
    items(transactionsUiItems.itemCount) {
        val item = transactionsUiItems[it]
        if (item is TransactionUiState.Date) {
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                text = item.date,
                style = MaterialTheme.typography.headlineSmall
            )
        } else if (item is TransactionUiState.Item) {
            TransactionItem(
                icon = item.transaction.category.iconRes,
                name = item.transaction.category.nameRes,
                expense = item.transaction.formattedExpense,
                date = item.transaction.date
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ExpensesTrackerTheme {
        HomeScreen(
            balanceUiState = BalanceUiState.Empty,
            depositUiState = DepositUiState.Empty,
            transactionsUiItems = MutableStateFlow(PagingData.empty<TransactionUiState>()).collectAsLazyPagingItems(),
            onDepositClick = {},
            onAddTransactionClick = {},
            onDepositValueChange = {},
            onDepositSaveClick = {},
            onDepositCloseClick = {}
        )
    }
}