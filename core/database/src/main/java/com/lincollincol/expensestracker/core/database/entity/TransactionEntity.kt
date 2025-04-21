package com.lincollincol.expensestracker.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey
    val id: String,
    val accountId: String,
    val amount: String,
    val category: String,
    val date: Long
)