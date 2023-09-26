package com.hamidrezabashiri.ezcard.ui.screens.addCard

import com.hamidrezabashiri.ezcard.utils.BankNameDetector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import com.hamidrezabashiri.ezcard.utils.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val repository: CardRepository,
    val bankNameDetector: BankNameDetector,
) : ViewModel() {


    val loginState = MutableStateFlow<LoginState>(LoginState.Idle)

    var fullName by mutableStateOf("")
        private set

    fun onFullNameChanged(newName: String) {
        fullName = newName
    }

    var cardNumber by mutableStateOf("")
        private set

    fun onCardNumberChanged(new: String) {
        cardNumber = new
    }

    var iban by mutableStateOf("")
        private set

    fun onIbanChanged(new: String) {
        iban = new
    }

    var accountNumber by mutableStateOf("")
        private set

    fun onAccountNumberChanged(new: String) {
        accountNumber = new
    }

    var dateYear by mutableStateOf("")
        private set

    fun onDateYearChanged(new: String) {
        dateYear = new
    }

    var dateMonth by mutableStateOf("")
        private set

    fun onDateMonthChanged(new: String) {
        dateMonth = new
    }

    var cvv2 by mutableStateOf("")
        private set

    fun onCvv2Changed(new: String) {
        cvv2 = new
    }

    fun addCard(creditCard: CreditCard) = viewModelScope.launch {
        loginState.value = LoginState.Loading

        try {
            repository.insertCard(creditCard)
            loginState.value = LoginState.Success("")
        } catch (e: Exception) {
            loginState.value = LoginState.Error("")
        }
    }
}