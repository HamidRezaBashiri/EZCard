package com.hamidrezabashiri.ezcard.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cardRepository: CardRepository) : ViewModel() {

    val cardListFlow: Flow<List<CreditCard>> = cardRepository.getAllCards()

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
    fun onDeleteCard(creditCard: CreditCard): Boolean {
        return try {
            viewModelScope.launch {
                cardRepository.deleteCard(creditCard)
            }
            true

        } catch (e: Exception) {
            false
        }

    }

//    fun onCopyToClipBoardClicked(string: String){
//
//    }

}