package com.lincollincol.expensestracker.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.lincollincol.expensestracker.core.database.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: TransactionEntity)

    @Insert
    suspend fun insert(transactions: List<TransactionEntity>)

    @Transaction
    @Query("SELECT * FROM TransactionEntity ORDER BY date DESC")
    fun selectTransactions(): PagingSource<Int, TransactionEntity>

}