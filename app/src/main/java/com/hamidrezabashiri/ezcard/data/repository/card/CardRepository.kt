package com.hamidrezabashiri.ezcard.data.repository.card

import androidx.annotation.WorkerThread
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.data_source.local.room.CardDao
import kotlinx.coroutines.flow.Flow

class CardRepository(private val cardDao: CardDao) {

    val creditCardList: Flow<List<CreditCard>> = cardDao.getAllCards()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCreditCard(creditCard: CreditCard){
        cardDao.addCard(creditCard)
    }
}