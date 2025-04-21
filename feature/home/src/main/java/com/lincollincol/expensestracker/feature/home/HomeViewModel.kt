package com.lincollincol.expensestracker.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lincollincol.expensestracker.core.common.REGEX_PATTERN_CURRENCY_INPUT
import com.lincollincol.expensestracker.core.data.AccountRepository
import com.lincollincol.expensestracker.core.data.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val exchangeRepository: ExchangeRepository,
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> = accountRepository.getTransactionsStream()
        .map { transactions ->
            HomeUiState(
                balanceBtc = 0F,
                balanceUsd = 0F,
                exchangeRateBtcUsd = 0F,
                transactions = transactions.groupBy { formatTimestamp(it.date) }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState.Empty
        )

    private val _depositUiState = MutableStateFlow(DepositUiState.Empty)
    val depositUiState get() = _depositUiState.asStateFlow()

    private val depositInputRegex = Regex(REGEX_PATTERN_CURRENCY_INPUT)

    fun test() {
        viewModelScope.launch {
            val rate = exchangeRepository.getExchangeRate("")
            println("=> $rate")
        }
    }

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

    private fun formatTimestamp(timestamp: Long): String {
        val zoneId = ZoneId.systemDefault()
        val today = LocalDate.now(zoneId)
        return when (val date = Instant.ofEpochMilli(timestamp).atZone(zoneId).toLocalDate()) {
            today -> "Today"
            today.minusDays(1) -> "Yesterday"
            else -> date.format(DateTimeFormatter.ofPattern("MMMM d", Locale.getDefault()))
        }
    }

}