package com.lincollincol.expensestracker.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.insertSeparators
import androidx.paging.map
import com.lincollincol.expensestracker.core.common.REGEX_PATTERN_CURRENCY_INPUT
import com.lincollincol.expensestracker.core.data.AccountRepository
import com.lincollincol.expensestracker.core.data.ExchangeRepository
import com.lincollincol.expensestracker.core.data.TransactionRepository
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.extensions.parseFloatInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    exchangeRepository: ExchangeRepository,
    transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository,
) : ViewModel() {

    val balanceUiState: StateFlow<BalanceUiState> = combine(
        accountRepository.getCryptoAccountStream(),
        exchangeRepository.getAccountExchangeRateStream()
    ) { account, exchangeRate ->
        BalanceUiState(
            balanceBtc = account.balance,
            balanceUsd = exchangeRate?.priceUsd?.times(account.balance),
            exchangeRateBtcUsd = exchangeRate?.priceUsd,
            exchangeRateChangePercent = exchangeRate?.changePercent,
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BalanceUiState.Empty
        )

    val transactionsUiState = transactionRepository.getTransactionsStream()
        .map { paging ->
            paging.map { TransactionUiState.Item(it) }
                .insertSeparators { t1: TransactionUiState.Item?, t2: TransactionUiState.Item? ->
                    val beforeDate = t1?.transaction?.date
                    val afterDate = t2?.transaction?.date ?: return@insertSeparators null


                    val label = formatTimestamp(afterDate)
                    val beforeLabel = beforeDate?.let { formatTimestamp(it) }

                    if (label != beforeLabel) {
                        TransactionUiState.Date(label)
                    } else null
                }

        }

    private val _depositUiState = MutableStateFlow(DepositUiState.Empty)
    val depositUiState get() = _depositUiState.asStateFlow()

    private val depositInputRegex = Regex(REGEX_PATTERN_CURRENCY_INPUT)

    fun updateDepositValue(value: String) {
        _depositUiState.update {
            val input = when {
                value.isEmpty() || depositInputRegex.matches(value) -> value
                else -> it.input
            }
            it.copy(input = input)
        }
    }

    fun saveDepositValue() {
        viewModelScope.launch {
            val amount = depositUiState.value.input.parseFloatInput()
            accountRepository.depositToAccountBalance(amount)
            _depositUiState.update { DepositUiState.Empty }
            cancelDepositToBalance()
        }
    }

    fun depositToBalance() {
        val exchangeRate = balanceUiState.value.exchangeRateBtcUsd
        _depositUiState.update {
            it.copy(exchangeRateBtcUsd = exchangeRate ?: 0F, isVisible = true)
        }
    }

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