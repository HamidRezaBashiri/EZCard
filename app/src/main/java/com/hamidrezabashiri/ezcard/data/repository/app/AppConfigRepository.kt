package com.hamidrezabashiri.ezcard.data.repository.app

import com.hamidrezabashiri.ezcard.model.ThemeMode
import kotlinx.coroutines.flow.Flow


interface AppConfigRepository {

    fun getAppTheme(): Flow<ThemeMode>

    suspend fun setAppTheme(themeMode: ThemeMode)

    suspend fun setAppDefaultLanguage(languageTag: String)

    fun getAppDefaultLanguageTag(): Flow<String>

}