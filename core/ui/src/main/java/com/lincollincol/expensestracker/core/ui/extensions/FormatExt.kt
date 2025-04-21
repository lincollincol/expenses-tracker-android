package com.lincollincol.expensestracker.core.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.lincollincol.expensestracker.core.common.DF_PATTERN_CURRENCY_PREVIEW
import com.lincollincol.expensestracker.core.common.SDF_PATTERN_HMS_24H
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
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

@Composable
fun rememberTimeFormatter(
    locale: Locale = Locale.getDefault(),
    pattern: String = SDF_PATTERN_HMS_24H
): SimpleDateFormat {
    return remember {
        SimpleDateFormat(pattern, locale)
    }
}