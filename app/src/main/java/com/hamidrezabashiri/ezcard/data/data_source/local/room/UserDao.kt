package com.hamidrezabashiri.ezcard.data.data_source.local.room

import androidx.room.*
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.dataModel.User
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers() : List<User>

}