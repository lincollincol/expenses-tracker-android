package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.ExchangeRate
import jakarta.inject.Inject

internal class ExchangeRepositoryImpl @Inject constructor(): ExchangeRepository {
    override suspend fun getExchangeRate(currency: String): ExchangeRate {
        error("Not implemented!")
    }
}