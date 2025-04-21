package com.lincollincol.expensestracker.core.network

import com.lincollincol.expensestracker.core.network.model.ApiResponse
import com.lincollincol.expensestracker.core.network.model.ExchangeRateApiModel
import retrofit2.http.GET

interface ExchangeApiService {
    @GET("/v3/assets/bitcoin")
    suspend fun getExchangeRate(): ApiResponse<ExchangeRateApiModel>
}