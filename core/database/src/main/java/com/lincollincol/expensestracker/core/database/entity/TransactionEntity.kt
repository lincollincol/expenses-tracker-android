package com.lincollincol.expensestracker.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val currency: String,
    val amount: Float,
    val category: String,
    val date: Long
)