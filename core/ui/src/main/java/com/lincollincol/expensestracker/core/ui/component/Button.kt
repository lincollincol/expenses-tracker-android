package com.lincollincol.expensestracker.core.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ETButton(
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true,
    onClick: () -> Unit,
    text: String,
) {
    ButtonDefaults.ContentPadding
    Button(
        modifier = Modifier.defaultMinSize(minHeight = 56.dp).then(modifier),
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 16.dp
        ),
        shape = RoundedCornerShape(14.dp),
        colors = colors,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun ETButtonPreview() {
    ETButton(
        onClick = {},
        text = "Button"
    )
}
