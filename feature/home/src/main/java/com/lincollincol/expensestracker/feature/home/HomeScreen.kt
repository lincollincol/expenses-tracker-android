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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.component.SectionHeading
import com.lincollincol.expensestracker.core.ui.extensions.formattedExpense
import com.lincollincol.expensestracker.core.ui.extensions.iconRes
import com.lincollincol.expensestracker.core.ui.extensions.nameRes
import com.lincollincol.expensestracker.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()
    val depositUiState by viewModel.depositUiState.collectAsStateWithLifecycle()

    HomeScreen(
        homeUiState = homeUiState,
        depositUiState = depositUiState,
        onAddTransactionClick = onAddTransactionClick,

        // Screen-level events
//        onDepositClick = viewModel::depositToBalance,
        onDepositClick = { viewModel.test() },
        onDepositValueChange = viewModel::updateDepositValue,
        onDepositSaveClick = viewModel::saveDepositValue,
        onDepositCloseClick = viewModel::cancelDepositToBalance
    )
}

@Composable
internal fun HomeScreen(
    homeUiState: HomeUiState,
    depositUiState: DepositUiState,
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
            balanceBtc = homeUiState.balanceBtc,
            balanceUsd = homeUiState.balanceUsd,
            exchangeRateBtcUsd = homeUiState.exchangeRateBtcUsd,
            onAddFundsClick = onDepositClick,
            onAddTransactionClick = onAddTransactionClick
        )
        expensesSection(homeUiState.transactions)
    }
    if (depositUiState.isVisible) {
        DepositDialog(
            input = depositUiState.input,
            equivalent = depositUiState.equivalent,
            inputCurrency = depositUiState.inputCurrency,
            equivalentCurrency = depositUiState.equivalentCurrency,
            onDepositValueChange = onDepositValueChange,
            onDepositSaveClick = onDepositSaveClick,
            onDismiss = onDepositCloseClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.balanceSection(
    balanceBtc: Float,
    balanceUsd: Float,
    exchangeRateBtcUsd: Float,
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    stickyHeader {
        SectionHeading(text = "Balance") {
            val formatter = rememberCurrencyValueFormatter()
            Text(
                modifier = Modifier.weight(0.5F),
                text = "1 BTC = ${formatter.format(exchangeRateBtcUsd)} USD",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )
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
    transactions: Map<String, List<Transaction>>
) {
    stickyHeader {
        SectionHeading(text = "Expenses")
    }
    transactions.onEach { transaction ->
        item {
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                text = transaction.key,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        items(transaction.value) {
            TransactionItem(
                icon = it.category.iconRes,
                name = it.category.nameRes,
                expense = it.formattedExpense,
                date = it.date
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ExpensesTrackerTheme {
        HomeScreen(
            homeUiState = HomeUiState.Empty,
            depositUiState = DepositUiState.Empty,
            onDepositClick = {},
            onAddTransactionClick = {},
            onDepositValueChange = {},
            onDepositSaveClick = {},
            onDepositCloseClick = {}
        )
    }
}