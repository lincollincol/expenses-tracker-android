package com.lincollincol.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(route = HomeScreen, navOptions)

fun NavGraphBuilder.homeScreen(
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) = composable<HomeScreen> {
    HomeRoute(
        onAddFundsClick = onAddFundsClick,
        onAddTransactionClick = onAddTransactionClick
    )
}