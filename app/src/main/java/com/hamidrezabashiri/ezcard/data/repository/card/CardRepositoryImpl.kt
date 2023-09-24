package com.hamidrezabashiri.ezcard.data.repository.card

import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.data_source.local.room.CardDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(private val cardDao: CardDao) : CardRepository {
    override suspend fun insertCard(creditCard: CreditCard): Boolean {
        return try {
            cardDao.addCard(creditCard)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getAllCards(): Flow<List<CreditCard>> {

        return try {
            cardDao.getAllCards()
        } catch (e: Exception) {
            flowOf(emptyList()) // Return an empty list as a Flow in case of an error.
        }
    }

    override fun getCardByHolderName(holderName: String): Flow<List<CreditCard>> {
        return try {
            cardDao.getCardsByHolderName(cardHolderName = holderName)
        } catch (e: Exception) {
            flowOf()
        }
    }

    override fun getCardById(id: Int): Flow<CreditCard> {
        return try {
            cardDao.getCardById(id)
        } catch (e: Exception) {
            flowOf()
        }
    }

    override suspend fun deleteCard(creditCard: CreditCard): Boolean {
        return try {
            cardDao.deleteCard(creditCard)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun updateCard(creditCard: CreditCard): Boolean {
        return try {
            cardDao.updateCard(creditCard)
            true
        } catch (e: Exception) {
            false
        }
    }
}