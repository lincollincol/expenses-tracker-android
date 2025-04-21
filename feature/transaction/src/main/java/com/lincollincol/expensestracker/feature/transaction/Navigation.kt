package com.lincollincol.expensestracker.feature.transaction

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import soup.compose.material.motion.animation.materialSharedAxisYIn
import soup.compose.material.motion.animation.materialSharedAxisYOut

@Serializable
object TransactionScreen

fun NavController.navigateToTransaction(navOptions: NavOptions? = null) =
    navigate(route = TransactionScreen, navOptions)

fun NavGraphBuilder.transactionScreen(
    onBackClick: () -> Unit
) {
    composable<TransactionScreen>(
        enterTransition = { materialSharedAxisYIn(true, 350) },
        exitTransition = { materialSharedAxisYOut(false, 350) },
        popExitTransition = { materialSharedAxisYOut(false, 350) }
    ) {
        TransactionRoute(onBackClick = onBackClick)
    }
}