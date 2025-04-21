package com.lincollincol.feature.home

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
    val amount: Float,
    val equivalent: Float,
    val isVisible: Boolean
) {
    companion object {
        val Empty get() = DepositUiState(0F, 0F, false)
    }
}
