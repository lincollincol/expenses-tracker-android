package com.lincollincol.expensestracker.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T : Any>(val data: T)