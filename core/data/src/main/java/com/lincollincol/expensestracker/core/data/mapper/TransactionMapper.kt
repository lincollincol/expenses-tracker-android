package com.lincollincol.expensestracker.core.data.mapper

import com.lincollincol.expensestracker.core.database.entity.TransactionEntity
import com.lincollincol.expensestracker.core.model.Currency
import com.lincollincol.expensestracker.core.model.Transaction

fun Transaction.entity() = TransactionEntity(
    id = id,
    currency = currency.name,
    amount = amount,
    category = category.name,
    date = date,
)

fun TransactionEntity.domain() = Transaction(
    id = id,
    currency = Currency.valueOf(currency),
    amount = amount,
    category = Transaction.Category.valueOf(category),
    date = date,
)