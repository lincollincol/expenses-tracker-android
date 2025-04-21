package com.lincollincol.expensestracker.feature.home

import com.lincollincol.expensestracker.core.model.Transaction
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

internal data class HomeUiState(
    val balanceBtc: Float,
    val balanceUsd: Float,
    val exchangeRateBtcUsd: Float,
    val transactions: Map<String, List<Transaction>>
) {
    companion object {
        val Empty get() = HomeUiState(
            0F,
            0F,
            0F,
            emptyMap()
        )
    }
}

internal data class DepositUiState(
    val input: String?,
    val equivalent: Float,
    val inputCurrency: String,
    val equivalentCurrency: String,
    val isVisible: Boolean
) {
    companion object {
        val Empty get() = DepositUiState(
            input = null,
            equivalent = 0F,
            inputCurrency = "BTC",
            equivalentCurrency = "USD",
            isVisible = false
        )
    }
}