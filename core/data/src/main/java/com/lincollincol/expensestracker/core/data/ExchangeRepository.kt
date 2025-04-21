package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.ExchangeRate

interface ExchangeRepository {
    suspend fun getExchangeRate(currency: String): ExchangeRate
}