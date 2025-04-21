package com.lincollincol.expensestracker.core.data

import com.lincollincol.expensestracker.core.model.CryptoAccount
import com.lincollincol.expensestracker.core.model.Transaction
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.random.nextInt

internal class AccountRepositoryImpl @Inject constructor() : AccountRepository {

    override suspend fun makeTransaction(
        account: CryptoAccount,
        amount: Float,
        category: Transaction.Category,
    ) {

    }

    override fun getTransactionsStream(): Flow<List<Transaction>> = flow {
        val dayInMillis = 86400000L
        val transactions = buildList {
            val ts = System.currentTimeMillis()
            repeat(10) { day ->
                repeat(Random.nextInt(2..6)) { item ->
                    add(
                        Transaction(
                            Random.nextInt().toString(),
                            currency = "BTC",
                            amount = Random.nextInt(10..100).toFloat().toString(),
                            category = Transaction.Category.entries.random(),
                            date = ts - (day * dayInMillis)
                        )
                    )
                }
            }
        }
        emit(transactions)
    }

}