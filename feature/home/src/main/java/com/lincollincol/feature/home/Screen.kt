package com.lincollincol.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lincollincol.core.ui.theme.ExpensesTrackerTheme

@Composable
internal fun HomeRoute(
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    HomeScreen(
        onAddFundsClick = onAddFundsClick,
        onAddTransactionClick = onAddTransactionClick
    )
}

@Composable
internal fun HomeScreen(
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
    ) {
        balanceSection(
            onAddFundsClick = onAddFundsClick,
            onAddTransactionClick = onAddTransactionClick
        )
        expensesSection()
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.balanceSection(
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    stickyHeader {
        SectionHeading(text = "Balance") {
            Text(
                modifier = Modifier.weight(0.5F),
                text = "1 BTC = 85,773.45 USD",
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End
            )
        }
    }
    item {
        HomeHeader(
            onAddFundsClick = onAddFundsClick,
            onAddTransactionClick = onAddTransactionClick
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.expensesSection() {
    stickyHeader {
        SectionHeading(text = "Expenses")
    }
    item {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Today",
            style = MaterialTheme.typography.headlineSmall
        )
    }
    items(5) {
        TransactionItem()
    }
    item {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Yesterday",
            style = MaterialTheme.typography.headlineSmall
        )
    }
    items(8) {
        TransactionItem()
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ExpensesTrackerTheme {
        HomeScreen(
            onAddFundsClick = {},
            onAddTransactionClick = {}
        )
    }
}