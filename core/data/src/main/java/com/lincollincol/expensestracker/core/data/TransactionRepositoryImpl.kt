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
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

internal class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao
) : TransactionRepository {

    override suspend fun makeTransaction(amount: Float, category: Transaction.Category) {
        val dayInMillis = 86400000L
        val transactions = buildList {
            val ts = System.currentTimeMillis()
            repeat(50) { day ->
                repeat(Random.nextInt(2..6)) { item ->
                    add(
                        Transaction(
                            Random.nextInt().toString(),
                            currency = Currency.BTC,
                            amount = Random.nextFloat(),
                            category = Transaction.Category.entries.random(),
                            date = ts - (day * dayInMillis)
                        )
                    )
                }
            }
        }.map { it.entity() }

        transactionDao.insert(transactions)
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

    /*override fun getTransactionsStream(): Flow<List<Transaction>> = flow {
        val dayInMillis = 86400000L
        val transactions = buildList {
            val ts = System.currentTimeMillis()
            repeat(10) { day ->
                repeat(Random.nextInt(2..6)) { item ->
                    add(
                        Transaction(
                            Random.nextInt().toString(),
                            currency = Currency.BTC,
                            amount = Random.nextInt(10..100).toFloat().toString(),
                            category = Transaction.Category.entries.random(),
                            date = ts - (day * dayInMillis)
                        )
                    )
                }
            }
        }
        emit(transactions)
    }*/
}