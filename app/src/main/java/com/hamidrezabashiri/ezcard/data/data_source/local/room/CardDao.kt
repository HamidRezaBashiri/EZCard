package com.hamidrezabashiri.ezcard.data.data_source.local.room

import androidx.room.*
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCard(creditCard: CreditCard)

    @Update
    suspend fun updateCard(creditCard: CreditCard)

    @Delete
    suspend fun deleteCard(creditCard: CreditCard)

    @Query("SELECT * FROM card_table")
    fun getAllCards(): Flow<List<CreditCard>>

    @Query("SELECT * FROM card_table WHERE cardHolderName = :cardHolderName")
    fun getCardsByHolderName(cardHolderName: String): Flow<List<CreditCard>>

    @Query("SELECT * FROM card_table WHERE id = :id")
    fun getCardById(id: Int): Flow<CreditCard>

}