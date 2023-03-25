package com.hamidrezabashiri.ezcard.data.repository.card

import androidx.annotation.WorkerThread
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.data_source.local.room.CardDao
import kotlinx.coroutines.flow.Flow

interface CardRepository {
   suspend fun insertCard(creditCard: CreditCard)

   suspend fun getAllCards() :Flow<List<CreditCard>>

}