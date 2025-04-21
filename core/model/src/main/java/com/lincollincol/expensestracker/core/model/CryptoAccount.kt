package com.lincollincol.expensestracker.core.model

data class CryptoAccount(
    val id: String,
    val currencyId: String,
    val currency: String,
    val balance: Float
)