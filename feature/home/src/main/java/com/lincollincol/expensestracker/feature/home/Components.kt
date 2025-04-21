package com.lincollincol.expensestracker.feature.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lincollincol.expensestracker.core.ui.component.ETButton
import com.lincollincol.expensestracker.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.expensestracker.core.ui.extensions.rememberTimeFormatter
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme
import java.text.SimpleDateFormat

@Composable
internal fun TransactionItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    @StringRes name: Int,
    date: Long,
    expense: String,
    paddings: PaddingValues = PaddingValues(bottom = 4.dp),
) {
    val timeFormatter = rememberTimeFormatter()
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
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.75F)),
            painter = painterResource(icon),
            contentDescription = "",
            contentScale = ContentScale.None,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .weight(1F)
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
            text = expense,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
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
            expense = "0,01 BTC"
        )
    }
}

@Composable
internal fun BalanceBanner(
    modifier: Modifier = Modifier,
    balanceBtc: Float,
    balanceUsd: Float,
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    Column(modifier) {
        val formatter = rememberCurrencyValueFormatter()
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
                text = "${formatter.format(balanceBtc)} BTC"/*buildAnnotatedString {
                    append("0.84")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Normal)) {
                        append(" BTC")
                    }
                }*/,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "${formatter.format(balanceUsd)} USD",
                style = MaterialTheme.typography.titleMedium,
            )
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