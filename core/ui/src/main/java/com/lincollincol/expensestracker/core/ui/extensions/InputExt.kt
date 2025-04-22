package com.lincollincol.expensestracker.core.ui.extensions

fun String?.parseFloatInput(): Float =
    this?.trim()?.replace(',', '.')?.toFloatOrNull() ?: 0F