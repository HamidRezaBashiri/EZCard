package com.hamidrezabashiri.ezcard.data.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CreditCard(
    @PrimaryKey(autoGenerate = true)
    val id :Int? ,
    val cardHolderName: String,
    val cardNumber: String,
    val cvv2: String,
    val expirationDate: String,
    val bankName: String,
    val sheba: String,
    val details: String,
    val cardPass: String?,
    val pass2: String?
) {


}
