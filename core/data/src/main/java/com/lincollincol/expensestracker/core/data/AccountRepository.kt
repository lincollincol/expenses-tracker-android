package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.CryptoAccount
import com.lincollincol.expensestracker.core.model.Transaction
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun getCryptoAccount(): CryptoAccount
    suspend fun makeTransaction(
        account: CryptoAccount,
        amount: Float,
        category: Transaction.Category
    )
    fun getTransactionsStream(): Flow<List<Transaction>>
}