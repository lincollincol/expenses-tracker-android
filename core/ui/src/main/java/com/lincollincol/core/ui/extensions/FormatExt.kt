package com.lincollincol.core.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

@Composable
fun rememberCurrencyValueFormatter(
    locale: Locale = Locale.getDefault(),
    pattern: String = DF_PATTERN_CURRENCY_PREVIEW
): DecimalFormat {
    return remember {
        DecimalFormat(pattern, DecimalFormatSymbols(locale).apply {
            decimalSeparator = ','
            groupingSeparator = ' '
        })
    }
}