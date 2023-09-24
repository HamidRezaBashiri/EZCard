package com.hamidrezabashiri.ezcard.ui.screens.addCard

import BankNameDetector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val repository: CardRepository,
    private val bankNameDetector: BankNameDetector,
) : ViewModel() {

//    fun getAllCards() = viewModelScope.launch { repository.getAllCards() }

//    val allCreditCard:LiveData<List<CreditCard>> = repository.getAllCards().asLiveData()

    fun addCard(creditCard: CreditCard) = viewModelScope.launch {
        repository.insertCard(creditCard)
    }
}