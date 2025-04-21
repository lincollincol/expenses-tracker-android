package com.lincollincol.expensestracker.core.model

data class Transaction(
    val id: String,
    val currency: String,
    val amount: String,
    val category: Category,
    val date: Long
) {
    enum class Category {
        GROCERIES, TAXI, ELECTRONICS, RESTAURANT, OTHER
    }
}