package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.data.mapper.domain
import com.lincollincol.expensestracker.core.database.dao.AccountDao
import com.lincollincol.expensestracker.core.database.entity.CryptoAccountEntity
import com.lincollincol.expensestracker.core.model.CryptoAccount
import com.lincollincol.expensestracker.core.model.Currency
import com.lincollincol.expensestracker.core.model.Transaction
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.random.Random
import kotlin.random.nextInt

internal class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : AccountRepository {

    override fun getCryptoAccountStream(): Flow<CryptoAccount> {
        return accountDao.selectAccountStream().map(CryptoAccountEntity::domain)
    }

    override suspend fun getCryptoAccount(): CryptoAccount {
        val account = accountDao.selectAccount() ?: run {
            accountDao.insert(CryptoAccountEntity.INITIAL)
            accountDao.selectAccount()
        }
        return requireNotNull(account?.domain())
    }

}