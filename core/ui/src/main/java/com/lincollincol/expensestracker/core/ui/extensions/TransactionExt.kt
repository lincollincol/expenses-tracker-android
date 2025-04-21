package com.lincollincol.expensestracker.core.ui.extensions

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.R

@get:DrawableRes
val Transaction.Category.iconRes get() = when(this) {
    Transaction.Category.GROCERIES -> R.drawable.ic_groceries
    Transaction.Category.TAXI -> R.drawable.ic_taxi
    Transaction.Category.ELECTRONICS -> R.drawable.ic_electronics
    Transaction.Category.RESTAURANT -> R.drawable.ic_restaurant
    Transaction.Category.OTHER -> R.drawable.ic_other
}

@get:StringRes
val Transaction.Category.nameRes get() = when(this) {
    Transaction.Category.GROCERIES -> R.string.category_groceries
    Transaction.Category.TAXI -> R.string.category_taxi
    Transaction.Category.ELECTRONICS -> R.string.category_electronics
    Transaction.Category.RESTAURANT -> R.string.category_restaurant
    Transaction.Category.OTHER -> R.string.category_other
}

val Transaction.formattedExpense get() = "$amount $currency"