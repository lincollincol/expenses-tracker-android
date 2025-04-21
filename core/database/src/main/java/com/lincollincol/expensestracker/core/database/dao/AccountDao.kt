package com.lincollincol.expensestracker.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Insert
    suspend fun insert(account: CryptoAccountEntity)

    @Query("SELECT * FROM CryptoAccountEntity LIMIT 1")
    suspend fun selectAccount(): CryptoAccountEntity?

    @Query("SELECT * FROM CryptoAccountEntity LIMIT 1")
    fun selectAccountStream(): Flow<CryptoAccountEntity>

}