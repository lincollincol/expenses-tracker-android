package com.lincollincol.feature.transaction

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import soup.compose.material.motion.animation.materialSharedAxisZIn
import soup.compose.material.motion.animation.materialSharedAxisZOut

@Serializable
object TransactionScreen

fun NavController.navigateToTransaction(navOptions: NavOptions? = null) =
    navigate(route = TransactionScreen, navOptions)

fun NavGraphBuilder.transactionScreen() {
    composable<TransactionScreen>(
        enterTransition = { materialSharedAxisZIn(true, 750) },
        exitTransition = { materialSharedAxisZOut(false, 750) },
    ) {
        TransactionRoute()
    }
}