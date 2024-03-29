package com.hamidrezabashiri.ezcard.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.hamidrezabashiri.ezcard.data.data_source.local.room.CardDao
import com.hamidrezabashiri.ezcard.data.data_source.local.room.EzCardRoomDatabase
import com.hamidrezabashiri.ezcard.data.data_source.local.room.UserDao
import com.hamidrezabashiri.ezcard.utils.BankNameDetector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAppDatabase(application: Application): EzCardRoomDatabase {
        return EzCardRoomDatabase.getDatabase(application)
    }

    @Provides
    fun provideUserDao(appDatabase: EzCardRoomDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideCardDao(appDatabase: EzCardRoomDatabase): CardDao {
        return appDatabase.cardDao()
    }

    @Provides
    fun provideBankNameDetector(): BankNameDetector {
        return BankNameDetector()
    }

}