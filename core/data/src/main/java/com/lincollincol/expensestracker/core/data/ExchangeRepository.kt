package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface ExchangeRepository {
    fun getAccountExchangeRateStream(): Flow<ExchangeRate?>
    suspend fun syncExchangeRate(currencyId: String)
    suspend fun getExchangeRate(currencyId: String): ExchangeRate?
    suspend fun getAccountExchangeRate(): ExchangeRate?
}