package com.hamidrezabashiri.ezcard.ui.screens.addCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import kotlinx.coroutines.launch

class AddCardViewModel(private val repository: CardRepository) : ViewModel() {

    fun getAllCards() = viewModelScope.launch {  repository.getAllCards()}

//    val allCreditCard:LiveData<List<CreditCard>> = repository.getAllCards().asLiveData()

    fun addCard(creditCard: CreditCard) = viewModelScope.launch{
        repository.insertCard(creditCard)
    }
}