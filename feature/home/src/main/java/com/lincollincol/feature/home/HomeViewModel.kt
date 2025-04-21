package com.lincollincol.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class HomeViewModel : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState.Empty)
    val homeUiState get() = _homeUiState.asStateFlow()

    private val _depositUiState = MutableStateFlow(DepositUiState.Empty)
    val depositUiState get() = _depositUiState.asStateFlow()

    fun updateDepositValue(value: String) {

    }

    fun saveDepositValue() {

    }

}