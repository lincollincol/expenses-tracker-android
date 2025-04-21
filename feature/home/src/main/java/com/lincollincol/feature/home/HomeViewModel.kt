package com.lincollincol.feature.home

import androidx.lifecycle.ViewModel
import com.lincollincol.core.ui.extensions.REGEX_PATTERN_CURRENCY_INPUT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class HomeViewModel : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState.Empty)
    val homeUiState get() = _homeUiState.asStateFlow()

    private val _depositUiState = MutableStateFlow(DepositUiState.Empty)
    val depositUiState get() = _depositUiState.asStateFlow()

    private val depositInputRegex = Regex(REGEX_PATTERN_CURRENCY_INPUT)

    fun updateDepositValue(value: String) {
        _depositUiState.update {
            val input = when {
                value.isEmpty() || depositInputRegex.matches(value) -> value
                else -> it.input
            }
            // TODO: calculate equivalent based on exchangeRateBtcUsd
            // val equivalent = homeUiState.value.exchangeRateBtcUsd
            it.copy(input = input)
        }
    }

    fun saveDepositValue() {
        // TODO: update balance
        cancelDepositToBalance()
    }

    fun depositToBalance() = _depositUiState.update { it.copy(isVisible = true) }

    fun cancelDepositToBalance() = _depositUiState.update { it.copy(isVisible = false) }

}