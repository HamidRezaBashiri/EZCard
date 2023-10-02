package com.hamidrezabashiri.ezcard.ui.screens.wallet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.app.AppConfigRepository
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import com.hamidrezabashiri.ezcard.model.ThemeMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(private val cardRepository: CardRepository,private val appConfigRepository: AppConfigRepository) :
    ViewModel() {
    private val _appThemeState = mutableStateOf<ThemeMode?>(ThemeMode.SYSTEM)
    val appThemeState: State<ThemeMode?> = _appThemeState

    init {
        viewModelScope.launch {
            appConfigRepository.getAppTheme().collect { themeMode ->
                _appThemeState.value = themeMode
            }
        }
    }
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