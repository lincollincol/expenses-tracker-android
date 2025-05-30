package com.lincollincol.expensestracker.feature.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lincollincol.expensestracker.core.common.DF_PATTERN_BTC_PREVIEW
import com.lincollincol.expensestracker.core.common.DF_PATTERN_EXPENSE_PREVIEW
import com.lincollincol.expensestracker.core.common.DF_PATTERN_USD_PREVIEW
import com.lincollincol.expensestracker.core.ui.component.ETButton
import com.lincollincol.expensestracker.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.expensestracker.core.ui.extensions.rememberTimeFormatter
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme

@Composable
internal fun TransactionItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes name: Int,
    date: Long,
    expense: Float,
    currency: String,
    paddings: PaddingValues = PaddingValues(bottom = 4.dp),
) {
    val timeFormatter = rememberTimeFormatter()
    val currencyFormatter = rememberCurrencyValueFormatter(pattern = DF_PATTERN_EXPENSE_PREVIEW)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddings)
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(12.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.85F)),
            painter = painterResource(icon),
            contentDescription = stringResource(name),
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = timeFormatter.format(date),
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Normal
            )
        }
        Text(
            modifier = Modifier.weight(1F),
            text = "${currencyFormatter.format(expense)} $currency",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.End,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}


@Preview
@Composable
private fun TransactionItemPreview() {
    ExpensesTrackerTheme {
        TransactionItem(
            icon = com.lincollincol.expensestracker.core.ui.R.drawable.ic_btc,
            name = com.lincollincol.expensestracker.core.ui.R.string.category_other,
            date = System.currentTimeMillis(),
            expense = 0.0001F,
            currency = "BTC"
        )
    }
}

@Composable
internal fun BalanceBanner(
    modifier: Modifier = Modifier,
    balanceBtc: Float,
    balanceUsd: Float?,
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    Column(modifier) {
        val btcFormatter = rememberCurrencyValueFormatter(pattern = DF_PATTERN_BTC_PREVIEW)
        val usdFormatter = rememberCurrencyValueFormatter(pattern = DF_PATTERN_USD_PREVIEW)
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
                .padding(vertical = 24.dp)
                .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${btcFormatter.format(balanceBtc)} BTC",
                style = MaterialTheme.typography.titleLarge,
            )
            if (balanceUsd != null) {
                Text(
                    text = "${usdFormatter.format(balanceUsd)} USD",
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ETButton(
                modifier = Modifier.weight(1F),
                text = "Deposit",
                onClick = onAddFundsClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )
            ETButton(
                modifier = Modifier.weight(1F),
                text = " Transaction",
                onClick = onAddTransactionClick
            )
        }
    }
}

@Preview
@Composable
private fun BalanceBannerPreview() {
    ExpensesTrackerTheme {
        BalanceBanner(
            balanceBtc = 0F,
            balanceUsd = 0F,
            onAddFundsClick = {},
            onAddTransactionClick = {}
        )
    }
}