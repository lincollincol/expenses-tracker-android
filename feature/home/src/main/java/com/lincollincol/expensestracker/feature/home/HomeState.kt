package com.lincollincol.expensestracker.feature.home

internal data class HomeUiState(
    val balanceBtc: Float,
    val balanceUsd: Float,
    val exchangeRateBtcUsd: Float,
) {
    companion object {
        val Empty get() = HomeUiState(0F, 0F, 0F)
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
