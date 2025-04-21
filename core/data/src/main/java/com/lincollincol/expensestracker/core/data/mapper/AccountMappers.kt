package com.lincollincol.expensestracker.core.data.mapper

import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import com.lincollincol.expensestracker.core.model.CryptoAccount

internal fun CryptoAccountEntity.domain() = CryptoAccount(
    id = id,
    currencyId = currencyId,
    currency = currency,
    balance = balance
)