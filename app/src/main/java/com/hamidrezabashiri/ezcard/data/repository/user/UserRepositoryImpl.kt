package com.hamidrezabashiri.ezcard.data.repository.user

import com.hamidrezabashiri.ezcard.data.dataModel.User
import com.hamidrezabashiri.ezcard.data.data_source.local.room.UserDao
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) :UserRepository {
    override suspend fun addMainUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun addCardUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyMainUserPassword(user: User) {
        TODO("Not yet implemented")
    }

}