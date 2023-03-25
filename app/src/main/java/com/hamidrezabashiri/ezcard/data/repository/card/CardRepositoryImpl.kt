package com.hamidrezabashiri.ezcard.data.repository.card

import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.data_source.local.room.CardDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardDao: CardDao) : CardRepository {
    override suspend fun insertCard(creditCard: CreditCard) {
        cardDao.addCard(creditCard)
    }

    override suspend fun getAllCards(): Flow<List<CreditCard>> {
        return cardDao.getAllCards()
    }
}