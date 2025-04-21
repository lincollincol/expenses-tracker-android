package com.lincollincol.expensestracker.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme

@Composable
fun NumberInput(
    modifier: Modifier = Modifier,
    value: String,
    suffix: String,
    onValueChange: (String) -> Unit,
    onSuffixClick: () -> Unit = {},
) {
    TextField(
        modifier = Modifier
            .then(modifier)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(10.dp)
            ),
        value = value,
        placeholder = { Text("0") },
        onValueChange = onValueChange,
        maxLines = 1,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        suffix = {
            Text(text = suffix)
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        textStyle = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
    )

}

@Preview
@Composable
private fun NumberInputPreview() {
    ExpensesTrackerTheme {
        NumberInput(
            value = "",
            suffix = "USD",
            onValueChange = {},
            onSuffixClick = {}
        )
    }
}

@Composable
fun SectionHeading(
    modifier: Modifier = Modifier,
    text: String,
    trailingContent: @Composable RowScope.() -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
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
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Preview
@Composable
private fun SectionHeadingPreview() {
    ExpensesTrackerTheme {
        SectionHeading(text = "Section") {  }
    }
}