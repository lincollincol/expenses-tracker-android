package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.data.mapper.domain
import com.lincollincol.expensestracker.core.model.ExchangeRate
import com.lincollincol.expensestracker.core.network.ExchangeApiService
import jakarta.inject.Inject

internal class ExchangeRepositoryImpl @Inject constructor(
    private val service: ExchangeApiService
): ExchangeRepository {
    override suspend fun getExchangeRate(currency: String): ExchangeRate {
//        error("")
        return service.getExchangeRate().data.domain()
    }
}