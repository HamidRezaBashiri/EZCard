package com.hamidrezabashiri.ezcard.ui.screens.confirmDelete

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmDeleteViewModel @Inject constructor(private val cardRepository: CardRepository) :
    ViewModel() {

    var card = mutableStateOf(CreditCard())

    fun getCardById(id: Int) {
        viewModelScope.launch {
            val newCard = cardRepository.getCardById(id).first()
            card.value = newCard
        }
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
}