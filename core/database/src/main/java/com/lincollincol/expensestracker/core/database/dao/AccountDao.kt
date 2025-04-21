package com.lincollincol.expensestracker.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import com.lincollincol.expensestracker.core.database.entity.TransactionEntity
import com.lincollincol.expensestracker.core.database.model.AccountTransaction

@Dao
interface AccountDao {

    @Insert
    suspend fun insert(account: CryptoAccountEntity)

    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Query("SELECT * FROM CryptoAccountEntity LIMIT 1")
    suspend fun selectAccount(): CryptoAccountEntity?

    @Transaction
    @Query("SELECT * FROM CryptoAccountEntity")
    suspend fun selectTransactions(): List<AccountTransaction>

}