package com.lincollincol.expensestracker.core.model

data class CryptoAccount(
    val id: String,
    val currency: String,
    val balance: Float
)