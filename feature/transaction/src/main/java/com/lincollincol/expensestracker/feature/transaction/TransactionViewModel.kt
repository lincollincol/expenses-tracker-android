package com.lincollincol.expensestracker.feature.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lincollincol.expensestracker.core.data.AccountRepository
import com.lincollincol.expensestracker.core.data.TransactionRepository
import com.lincollincol.expensestracker.core.model.Transaction
import com.lincollincol.expensestracker.core.ui.extensions.parseFloatInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TransactionViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _inputUiState = MutableStateFlow<String?>(null)
    val inputUiState get() = _inputUiState.asStateFlow()

    private val selectedCategory = MutableStateFlow<Transaction.Category?>(null)

    val transactionUiState = combine(
        inputUiState,
        selectedCategory,
        accountRepository.getCryptoAccountStream()
    ) { input, category, account ->
        val amount = input.parseFloatInput()
        TransactionUiState(
            balance = account.balance - amount,
            isValid = category != null && !input.isNullOrEmpty() && amount <= account.balance
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TransactionUiState.Empty
    )

    val categoriesUiState = selectedCategory.map { selected ->
        Transaction.Category.entries.map {
            CategoryItemUiState(it, it == selected)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _backEvent = MutableSharedFlow<Unit>()
    val backEvent get() = _backEvent.asSharedFlow()

    fun makeTransaction() {
        viewModelScope.launch {
            val amount = inputUiState.value.parseFloatInput()
            transactionRepository.makeTransaction(
                amount = amount,
                category = requireNotNull(selectedCategory.value)
            )
            accountRepository.withdrawFromAccountBalance(amount)
            _backEvent.emit(Unit)
        }
    }

    fun updateInput(value: String?) = _inputUiState.update { value }

    fun selectCategory(category: Transaction.Category) = selectedCategory.update { category }

}