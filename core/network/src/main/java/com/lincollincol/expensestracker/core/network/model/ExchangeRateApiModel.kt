package com.lincollincol.expensestracker.core.network.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateApiModel(
    val id: String,
    val symbol: String,
    val priceUsd: Float,
    val changePercent24Hr: Float
)