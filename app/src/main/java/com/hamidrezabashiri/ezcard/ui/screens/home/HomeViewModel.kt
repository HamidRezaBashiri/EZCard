package com.hamidrezabashiri.ezcard.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val cardRepository: CardRepository) : ViewModel() {

    val cardListFlow: Flow<List<CreditCard>> = cardRepository.getAllCards()



    fun onDeleteCard(creditCard: CreditCard){

    }

//    fun onCopyToClipBoardClicked(string: String){
//
//    }

}