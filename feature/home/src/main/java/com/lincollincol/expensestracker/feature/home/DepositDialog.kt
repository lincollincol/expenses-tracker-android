package com.lincollincol.expensestracker.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lincollincol.expensestracker.core.common.DF_PATTERN_USD_PREVIEW
import com.lincollincol.expensestracker.core.ui.component.ETButton
import com.lincollincol.expensestracker.core.ui.component.AmountInput
import com.lincollincol.expensestracker.core.ui.extensions.rememberCurrencyValueFormatter
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme

@Composable
fun DepositDialog(
    modifier: Modifier = Modifier,
    input: String?,
    equivalent: Float,
    inputCurrency: String,
    equivalentCurrency: String,
    onDepositValueChange: (String) -> Unit,
    onDepositSaveClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        val formatter = rememberCurrencyValueFormatter(pattern = DF_PATTERN_USD_PREVIEW)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp, vertical = 32.dp)
                .then(modifier),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Update Balance",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            AmountInput(
                modifier = Modifier.padding(vertical = 12.dp),
                value = input.orEmpty(),
                suffix = inputCurrency,
                onValueChange = onDepositValueChange
            )
            Text(
                text = "${formatter.format(equivalent)} $equivalentCurrency",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            )
            ETButton(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                text = "Save",
                onClick = onDepositSaveClick
            )
        }
    }
}

@Preview
@Composable
private fun DepositDialogPreview() {
    ExpensesTrackerTheme {
        DepositDialog(
            input = null,
            equivalent = 0F,
            inputCurrency = "BTC",
            equivalentCurrency = "USD",
            onDepositValueChange = {},
            onDepositSaveClick = {},
            onDismiss = {}
        )
    }
}