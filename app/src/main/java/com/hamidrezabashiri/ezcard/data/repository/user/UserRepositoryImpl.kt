package com.hamidrezabashiri.ezcard.data.repository.user

import com.hamidrezabashiri.ezcard.data.dataModel.User
import com.hamidrezabashiri.ezcard.data.data_source.local.room.UserDao
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun signupUser(user: User): Boolean {
        return try {
            val salt = BCrypt.gensalt()
            val hashedPassword = BCrypt.hashpw(user.password, salt)

            userDao.addUser(
                User(
                    name = user.name,
                    salt = salt,
                    password = hashedPassword,
                    isAdmin = true
                )
            )

            true
        } catch (e: Exception) {
            // Log the error or handle it more explicitly
            false
        }
    }

    override suspend fun loginUser(user: User): Boolean {
        return try {
            val adminUser = userDao.getAdminUser()
//            val hashedProvidedPassword = BCrypt.hashpw(user.password, adminUser.salt)
//            val isPasswordCorrect = hashedProvidedPassword == adminUser.password
            val isPasswordCorrect = BCrypt.checkpw(user.password, adminUser.password)


            isPasswordCorrect
        } catch (e: Exception) {
            // Log the error or handle it more explicitly
            false
        }
    }


    override suspend fun addCardUser(user: User): Boolean {
        return try {
            userDao.addUser(user)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getAllUsers(): List<User> {
        return try {
            userDao.getAllUsers()
        } catch (e: Exception) {
            listOf<User>()
        }
    }

    override suspend fun changePassword(user: User): Boolean {
        TODO("Not yet implemented")
    }

}