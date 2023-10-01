package com.hamidrezabashiri.ezcard.ui.screens.shareCard

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShareCardViewModel @Inject constructor(private val cardRepository: CardRepository) :
    ViewModel() {

    var card = mutableStateOf(CreditCard())

    val checkboxStates = mutableStateListOf(true, true, false, false, false, false)
    val checkboxTexts = listOf(
        "نام صاحب کارت",
        "شماره کارت",
        "شماره شبا",
        "شماره حساب",
        "تاریخ",
        "CVV2"
    )



    fun getCardById(id: Int) {
        viewModelScope.launch {
            val newCard = cardRepository.getCardById(id).first()
            card.value = newCard
        }
    }

    fun generateAndShareMessage(context: Context) {
        shareMessage(context = context, buildMessage())
    }


    private fun buildMessage(): String {
        val checkedItems = mutableListOf<String>()

        for (i in checkboxStates.indices) {
            if (checkboxStates[i]) {
                val cardinfo = when (i) {
                    0 -> card.value.cardHolderName
                    1 -> card.value.cardNumber
                    2 -> card.value.iban
                    3 -> card.value.accountNumber
                    4 -> card.value.expirationDate
                    5 -> card.value.cvv2
                    else -> ""
                }
                checkedItems.add("${checkboxTexts[i]}:\n$cardinfo")
            }
        }

        return checkedItems.joinToString("\n")
    }

    fun shareMessage(context: Context, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        val chooserIntent = Intent.createChooser(intent, "Share Message")
        context.startActivity(chooserIntent)
    }


}

