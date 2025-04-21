package com.lincollincol.expensestracker.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lincollincol.expensestracker.core.database.dao.AccountDao
import com.lincollincol.expensestracker.core.database.dao.ExchangeRateDao
import com.lincollincol.expensestracker.core.database.dao.TransactionDao
import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import com.lincollincol.expensestracker.core.database.entity.ExchangeRateEntity
import com.lincollincol.expensestracker.core.database.entity.TransactionEntity

@Database(
    entities = [CryptoAccountEntity::class, TransactionEntity::class, ExchangeRateEntity::class],
    version = 1
)
internal abstract class ETDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun exchangeRateDao(): ExchangeRateDao
    abstract fun transactionsDao(): TransactionDao
}