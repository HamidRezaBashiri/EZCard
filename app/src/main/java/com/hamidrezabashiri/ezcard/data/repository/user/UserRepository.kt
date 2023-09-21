package com.hamidrezabashiri.ezcard.data.repository.user

import com.hamidrezabashiri.ezcard.data.dataModel.User

interface UserRepository {
    suspend fun signupUser(user: User): Boolean
    suspend fun loginUser(user: User): Boolean
    suspend fun addCardUser(user: User): Boolean
    suspend fun getAllUsers(): List<User>

    suspend fun changePassword(user: User):Boolean
}