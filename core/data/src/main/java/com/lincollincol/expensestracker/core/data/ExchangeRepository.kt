package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.ExchangeRate

interface ExchangeRepository {
    suspend fun syncExchangeRate(currencyId: String)
    suspend fun getExchangeRate(currencyId: String): ExchangeRate?
    suspend fun getAccountExchangeRate(): ExchangeRate?
}