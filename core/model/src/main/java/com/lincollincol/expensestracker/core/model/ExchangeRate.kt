package com.lincollincol.expensestracker.core.model

data class ExchangeRate(
    val id: String,
    val currency: String,
    val priceUsd: Float,
    val changePercent: Float,
    val lastUpdate: Long,
)