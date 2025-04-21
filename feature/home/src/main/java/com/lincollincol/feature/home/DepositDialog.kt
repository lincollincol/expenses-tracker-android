package com.lincollincol.feature.home

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.lincollincol.core.ui.component.ETButton
import com.lincollincol.core.ui.component.NumberInput
import com.lincollincol.core.ui.theme.ExpensesTrackerTheme

@Composable
fun DepositDialog(
    modifier: Modifier = Modifier,
    onDepositValueChange: (String) -> Unit,
    onDepositSaveClick: () -> Unit,
) {
    Dialog(onDismissRequest = {  }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp, vertical = 32.dp),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = "Update Balance",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Amount",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            )
            NumberInput(
                modifier = Modifier.padding(vertical = 12.dp),
                value = "",
                suffix = "USD",
                onValueChange = onDepositValueChange,
                onSuffixClick = onDepositSaveClick
            )
            Text(
                text = "68 272,12 USD",
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
            )
            ETButton(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                text = "Save",
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun DepositDialogPreview() {
    ExpensesTrackerTheme {
        DepositDialog(
            onDepositValueChange = {},
            onDepositSaveClick = {}
        )
    }
}