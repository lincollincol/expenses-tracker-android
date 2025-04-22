package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.common.DAY_IN_MILLIS
import com.lincollincol.expensestracker.core.common.HOUR_IN_MILLIS
import com.lincollincol.expensestracker.core.data.mapper.domain
import com.lincollincol.expensestracker.core.data.mapper.entity
import com.lincollincol.expensestracker.core.database.dao.AccountDao
import com.lincollincol.expensestracker.core.database.dao.ExchangeRateDao
import com.lincollincol.expensestracker.core.database.entity.ExchangeRateEntity
import com.lincollincol.expensestracker.core.model.ExchangeRate
import com.lincollincol.expensestracker.core.network.ExchangeApiService
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext

internal class ExchangeRepositoryImpl @Inject constructor(
    private val service: ExchangeApiService,
    private val exchangeRateDao: ExchangeRateDao,
    private val accountDao: AccountDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ExchangeRepository {

    override suspend fun syncExchangeRate(currencyId: String) = withContext(ioDispatcher) {
        val localRate = exchangeRateDao.select(currencyId)
        if (localRate == null || localRate.elapsedTime > HOUR_IN_MILLIS) {
            val latestRate = service.getExchangeRate(currencyId).data?.entity()
                ?: return@withContext
            exchangeRateDao.upsert(latestRate)
        }
    }

    override suspend fun getExchangeRate(currencyId: String): ExchangeRate? {
        return exchangeRateDao.select(currencyId)?.domain()
    }

    override suspend fun getAccountExchangeRate(): ExchangeRate? {
        val account = accountDao.selectAccount() ?: return null
        return getExchangeRate(account.currencyId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAccountExchangeRateStream(): Flow<ExchangeRate?> {
        return flow { accountDao.selectAccount()?.let { emit(it.currencyId) } }
            .flatMapConcat { currencyId ->
                exchangeRateDao.selectStream(currencyId).map { it?.domain() }
            }
    }

}