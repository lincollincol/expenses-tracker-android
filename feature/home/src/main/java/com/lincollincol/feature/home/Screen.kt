package com.lincollincol.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun HomeRoute(
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    HomeScreen(
        onAddFundsClick = onAddFundsClick,
        onAddTransactionClick = onAddTransactionClick
    )
}

@Composable
internal fun HomeScreen(
    onAddFundsClick: () -> Unit,
    onAddTransactionClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = onAddFundsClick) {
                Text(text = "Add funds")
            }
            Button(onClick = onAddTransactionClick) {
                Text(text = "Add transaction")
            }
        }
    }
}