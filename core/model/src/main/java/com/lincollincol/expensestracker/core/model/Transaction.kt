package com.lincollincol.expensestracker.core.model

import java.util.UUID

data class Transaction(
    val id: String,
    val currency: Currency,
    val amount: Float,
    val category: Category,
    val date: Long
) {
    enum class Category {
        GROCERIES, TAXI, ELECTRONICS, RESTAURANT, OTHER
    }

    companion object {
        fun create(amount: Float, category: Category) = Transaction(
            id = UUID.randomUUID().toString(),
            currency = Currency.BTC,
            amount = amount,
            category = category,
            date = System.currentTimeMillis()
        )
    }

}