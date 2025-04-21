package com.lincollincol.expensestracker.core.data.mapper

import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import com.lincollincol.expensestracker.core.model.CryptoAccount
import com.lincollincol.expensestracker.core.model.Currency

internal fun CryptoAccountEntity.domain() = CryptoAccount(
    id = id,
    currency = Currency.valueOf(currency),
    balance = balance
)