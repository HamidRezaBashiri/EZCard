package com.hamidrezabashiri.ezcard.ui.screens.shareCard

import androidx.lifecycle.ViewModel
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShareCardViewModel @Inject constructor(cardRepository: CardRepository) : ViewModel() {
}