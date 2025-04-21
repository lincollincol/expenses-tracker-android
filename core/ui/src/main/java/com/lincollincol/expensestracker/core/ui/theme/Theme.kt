package com.lincollincol.expensestracker.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = VibrantOrange,
    secondary = DarkNavyBlue,
    background = LightGrey,
    surface = Color.White,
    onBackground = DarkNavyBlue,
    onPrimary = LightGrey,
    onSecondary = LightGrey,
    outline = MediumGrey
)

@Composable
fun ExpensesTrackerTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}