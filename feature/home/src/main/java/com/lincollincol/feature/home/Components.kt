package com.lincollincol.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.lincollincol.core.ui.component.ETButton
import com.lincollincol.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.core.ui.theme.ExpensesTrackerTheme
import java.text.DecimalFormat

@Composable
fun SectionHeading(
    text: String,
    trailingContent: @Composable RowScope.() -> Unit = {}
) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 24.dp, bottom = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
        )
        trailingContent(this)
    }
    HorizontalDivider(
        modifier = Modifier.padding(bottom = 24.dp),
        color = MaterialTheme.colorScheme.outline
    )
}

@Preview
@Composable
private fun SectionHeadingPreview() {
    ExpensesTrackerTheme {
        SectionHeading(text = "Section") {  }
    }
}

@Composable
internal fun TransactionItem(
    modifier: Modifier = Modifier,
    paddings: PaddingValues = PaddingValues(bottom = 4.dp)
) {
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
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.7F)),
            imageVector = Icons.Default.ShoppingCart,
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
                text = "Food",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = "12:56")
        }
        Text(
            text = "$16.23",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold

        )
    }
}


@Preview
@Composable
private fun TransactionItemPreview() {
    ExpensesTrackerTheme {
        TransactionItem()
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