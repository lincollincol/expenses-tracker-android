package com.lincollincol.expensestracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.lincollincol.expensestracker.MainViewModel
import com.lincollincol.expensestracker.core.ui.theme.ExpensesTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.prepareAccount()
        enableEdgeToEdge()
        setContent {
            ExpensesTrackerTheme {
                ExpensesTrackerApp()
            }
        }
    }

}