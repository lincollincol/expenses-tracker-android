package com.lincollincol.expensestracker.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.lincollincol.expensestracker.core.data.mapper.domain
import com.lincollincol.expensestracker.core.data.mapper.entity
import com.lincollincol.expensestracker.core.database.dao.TransactionDao
import com.lincollincol.expensestracker.core.database.entity.TransactionEntity
import com.lincollincol.expensestracker.core.model.Currency
import com.lincollincol.expensestracker.core.model.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun makeTransaction(amount: Float, category: Transaction.Category) {
        transactionDao.insert(Transaction.create(amount, category).entity())
    }

    override fun getTransactionsStream(): Flow<PagingData<Transaction>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { transactionDao.selectTransactions() }
        ).flow.map { it.map(TransactionEntity::domain) }
    }

}