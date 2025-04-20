package com.lincollincol.feature.transaction

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object TransactionScreen

fun NavController.navigateToTransaction(navOptions: NavOptions) =
    navigate(route = TransactionScreen, navOptions)

fun NavGraphBuilder.transactionScreen() =
    composable<TransactionScreen> { TransactionRoute() }