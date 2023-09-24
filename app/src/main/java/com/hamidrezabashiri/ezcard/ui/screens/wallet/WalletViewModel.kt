package com.hamidrezabashiri.ezcard.ui.screens.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(private val cardRepository: CardRepository) :
    ViewModel() {

    val cardListFlow: Flow<List<CreditCard>> = cardRepository.getAllCards()


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