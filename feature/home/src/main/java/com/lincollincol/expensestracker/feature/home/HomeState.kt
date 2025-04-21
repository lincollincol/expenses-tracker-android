package com.lincollincol.expensestracker.feature.home

import com.lincollincol.expensestracker.core.model.Currency
import com.lincollincol.expensestracker.core.model.Transaction

internal data class BalanceUiState(
    val balanceBtc: Float,
    val balanceUsd: Float?,
    val exchangeRateBtcUsd: Float?,
    val exchangeRateChangePercent: Float?
) {
    companion object {
        val Empty get() = BalanceUiState(
            0F,
            null,
            null,
            null
        )
    }
}

internal data class DepositUiState(
    val input: String?,
    val inputCurrency: Currency,
    val exchangeRateBtcUsd: Float,
    val isVisible: Boolean
) {
    companion object {
        val Empty get() = DepositUiState(
            input = null,
            inputCurrency = Currency.BTC,
            exchangeRateBtcUsd = 0F,
            isVisible = false
        )
    }

    private val inputNum get() = input?.replace(',', '.')?.toFloatOrNull() ?: 0F

    val equivalent: Float get() {
        return when(equivalentCurrency) {
            Currency.BTC -> inputNum / exchangeRateBtcUsd
            Currency.USD -> inputNum * exchangeRateBtcUsd
        }
    }

    val equivalentCurrency: Currency get() = when(inputCurrency) {
        Currency.BTC -> Currency.USD
        Currency.USD -> Currency.BTC
    }

}

internal sealed interface TransactionUiState {
    data class Item(val transaction: Transaction) : TransactionUiState
    data class Date(val date: String) : TransactionUiState
}