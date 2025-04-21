package com.lincollincol.core.ui.extensions

// Allow input from 1 to 10^12 (trillion) plus decimals
const val REGEX_PATTERN_CURRENCY_INPUT = "^\\d{1,12}(,\\d{0,2})?$"

// DF - decimal format
const val DF_PATTERN_CURRENCY_PREVIEW = "#,##0.00"