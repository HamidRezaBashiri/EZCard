package com.hamidrezabashiri.ezcard.data.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hamidrezabashiri.ezcard.R

@Entity(tableName = "card_table")
data class CreditCard(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var cardHolderName: String = "",
    var cardNumber: String = "",
    var accountNumber: String = "",
    var cvv2: String = "",
    var expirationDate: String = "",
    var bankName: String = "",
    var bankLogoResId: Int = R.drawable.chip,
    var iban: String = "",
    var details: String = "",
    var cardPass: String? = "",
    var pass2: String? = "",
) {


}
