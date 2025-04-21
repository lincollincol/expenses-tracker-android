package com.lincollincol.expensestracker.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExchangeRateEntity(
    @PrimaryKey
    val id: String,
    val currency: String,
    val priceUsd: Float,
    val changePercent: Float,
    val lastUpdate: Long
) {
    val elapsedTime get() = System.currentTimeMillis() - lastUpdate
}