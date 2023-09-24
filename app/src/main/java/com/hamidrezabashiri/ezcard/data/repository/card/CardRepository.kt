package com.hamidrezabashiri.ezcard.data.repository.card

import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun insertCard(creditCard: CreditCard): Boolean

    fun getAllCards(): Flow<List<CreditCard>>
     fun getCardByHolderName(holderName: String): Flow<List<CreditCard>>

     fun getCardById(id: Int): Flow<CreditCard>
    suspend fun deleteCard(creditCard: CreditCard): Boolean
    suspend fun updateCard(creditCard: CreditCard): Boolean

}