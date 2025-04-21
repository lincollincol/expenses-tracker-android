package com.lincollincol.expensestracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lincollincol.expensestracker.core.data.AccountRepository
import com.lincollincol.expensestracker.core.data.ExchangeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val exchangeRepository: ExchangeRepository
) : ViewModel() {
    fun prepareAccount() {
        viewModelScope.launch {
            val account = accountRepository.getCryptoAccount()
            exchangeRepository.syncExchangeRate(account.currency.id)
        }
    }
}