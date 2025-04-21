package com.lincollincol.expensestracker.core.network

import com.lincollincol.expensestracker.core.network.model.ApiResponse
import com.lincollincol.expensestracker.core.network.model.ExchangeRateApiModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeApiService {
    @GET("/v3/assets/{currencyId}")
    suspend fun getExchangeRate(
        @Path("currencyId") currencyId: String
    ): ApiResponse<ExchangeRateApiModel>
}