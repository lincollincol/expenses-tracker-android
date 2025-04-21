package com.lincollincol.expensestracker.core.data.mapper

import com.lincollincol.expensestracker.core.database.entity.ExchangeRateEntity
import com.lincollincol.expensestracker.core.model.ExchangeRate
import com.lincollincol.expensestracker.core.network.model.ExchangeRateApiModel

fun ExchangeRateEntity.domain() = ExchangeRate(
    id = id,
    currency = currency,
    priceUsd = priceUsd,
    changePercent = changePercent,
    lastUpdate = System.currentTimeMillis(),
)

fun ExchangeRateApiModel.domain() = ExchangeRate(
    id = id,
    currency = symbol,
    priceUsd = priceUsd,
    changePercent = changePercent24Hr,
    lastUpdate = System.currentTimeMillis(),
)

fun ExchangeRateApiModel.entity() = ExchangeRateEntity(
    id = id,
    currency = symbol,
    priceUsd = priceUsd,
    changePercent = changePercent24Hr,
    lastUpdate = System.currentTimeMillis(),
)