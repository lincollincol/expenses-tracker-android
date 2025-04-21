package com.lincollincol.expensestracker.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lincollincol.expensestracker.core.database.entity.ExchangeRateEntity

@Dao
interface ExchangeRateDao {

    @Upsert
    suspend fun upsert(rate: ExchangeRateEntity)

    @Query("SELECT * FROM ExchangeRateEntity WHERE id = :currencyId LIMIT 1")
    suspend fun select(currencyId: String): ExchangeRateEntity?

}