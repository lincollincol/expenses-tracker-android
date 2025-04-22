package com.lincollincol.expensestracker.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

fun NavGraphBuilder.homeScreen(
    onAddTransactionClick: () -> Unit
) = composable<HomeScreen> {
    HomeRoute(onAddTransactionClick = onAddTransactionClick)
}