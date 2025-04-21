package com.lincollincol.expensestracker.core.data

import androidx.paging.PagingData
import com.lincollincol.expensestracker.core.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun makeTransaction(
        amount: Float,
        category: Transaction.Category
    )
    fun getTransactionsStream(): Flow<PagingData<Transaction>>
}