package com.lincollincol.expensestracker.feature.transaction

import com.lincollincol.expensestracker.core.model.Transaction

data class TransactionUiState(
    val balance: Float,
    val isValid: Boolean
) {
    companion object {
        val Empty get() = TransactionUiState(0F, false)
    }
}

data class CategoryItemUiState(
    val category: Transaction.Category,
    val isSelected: Boolean
)