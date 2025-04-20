package com.lincollincol.expensestracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.lincollincol.expensestracker.ui.theme.ExpensestrackerTheme
import com.lincollincol.feature.home.HomeScreen
import com.lincollincol.feature.home.homeScreen
import com.lincollincol.feature.transaction.navigateToTransaction
import com.lincollincol.feature.transaction.transactionScreen

@Composable
fun ExpensesTrackerApp() {
    val navController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = HomeScreen
        ) {
            homeScreen(
                onAddFundsClick = {},
                onAddTransactionClick = { navController.navigateToTransaction(navOptions {  }) }
            )
            transactionScreen()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    ExpensestrackerTheme {
        ExpensesTrackerApp()
    }
}