package com.hamidrezabashiri.ezcard.di

import com.hamidrezabashiri.ezcard.data.repository.card.CardRepository
import com.hamidrezabashiri.ezcard.data.repository.card.CardRepositoryImpl
import com.hamidrezabashiri.ezcard.data.repository.user.UserRepository
import com.hamidrezabashiri.ezcard.data.repository.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun provideCardRepository(repositoryImpl: CardRepositoryImpl): CardRepository


}