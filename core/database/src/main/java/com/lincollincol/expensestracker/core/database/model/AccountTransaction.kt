package com.lincollincol.expensestracker.core.database.model

import androidx.room.Embedded
import androidx.room.Relation
import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import com.lincollincol.expensestracker.core.database.entity.TransactionEntity

data class AccountTransaction(
    @Embedded val account: CryptoAccountEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "accountId"
    )
    val transactions: List<TransactionEntity>
)