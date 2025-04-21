package com.lincollincol.expensestracker.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class CryptoAccountEntity(
    @PrimaryKey
    val id: String,
    val currencyId: String,
    val currency: String,
    val balance: Float
) {
    companion object {
        val INITIAL get() = CryptoAccountEntity(
            id = UUID.randomUUID().toString(),
            currencyId = "bitcoin",
            currency = "BTC",
            balance = 0F
        )
    }
}