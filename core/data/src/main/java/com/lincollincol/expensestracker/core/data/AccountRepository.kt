package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.CryptoAccount
import com.lincollincol.expensestracker.core.model.Transaction
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getCryptoAccountStream(): Flow<CryptoAccount>
    suspend fun getCryptoAccount(): CryptoAccount
}