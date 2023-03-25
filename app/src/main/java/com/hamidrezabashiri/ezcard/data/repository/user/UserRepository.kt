package com.hamidrezabashiri.ezcard.data.repository.user

import com.hamidrezabashiri.ezcard.data.dataModel.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addMainUser( user: User)
    suspend fun addCardUser(user: User)
    suspend fun getAllUsers():List<User>
   suspend fun verifyMainUserPassword(user: User)
}