package com.hamidrezabashiri.ezcard.ui.screens.editCard

import com.hamidrezabashiri.ezcard.utils.BankNameDetector
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import com.hamidrezabashiri.ezcard.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCardViewModel @Inject constructor(
    private val repository: CardRepository,
    val bankNameDetector: BankNameDetector,
) : ViewModel() {

    var card = mutableStateOf(CreditCard())

    fun getCardById(id: Int) {
        viewModelScope.launch {
            val newCard = repository.getCardById(id).first()
            card.value = newCard
            updateFieldsFromCard(newCard)

        }
    }

    val responseState = MutableStateFlow<ResponseState>(ResponseState.Idle)

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

    fun updateCard(creditCard: CreditCard) = viewModelScope.launch {
        responseState.value = ResponseState.Loading

        try {
            repository.updateCard(creditCard)
            responseState.value = ResponseState.Success("")
        } catch (e: Exception) {
            responseState.value = ResponseState.Error("")
        }
    }

    private fun updateFieldsFromCard(card: CreditCard) {
        cardNumber = card.cardNumber
        fullName = card.cardHolderName
        iban = card.iban
        accountNumber = card.accountNumber

        // Extract year and month from the expirationDate field, if it follows a specific format.
        // For example, if expirationDate is "12/23", you can split it to get year and month.
        val expirationParts = card.expirationDate.split("/")
        if (expirationParts.size == 2) {
            dateMonth = expirationParts[0]
            dateYear = expirationParts[1]
        } else {
            // Handle the case where the expirationDate format is different.
            dateMonth = ""
            dateYear = ""
        }

        cvv2 = card.cvv2
    }

}