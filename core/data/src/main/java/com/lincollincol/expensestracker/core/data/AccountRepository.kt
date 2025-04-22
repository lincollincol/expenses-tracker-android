package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.CryptoAccount
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getCryptoAccountStream(): Flow<CryptoAccount>
    suspend fun getCryptoAccount(): CryptoAccount
    suspend fun depositToAccountBalance(amount: Float)
    suspend fun withdrawFromAccountBalance(amount: Float)
}