package com.hamidrezabashiri.ezcard.data.repository.app

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hamidrezabashiri.ezcard.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppConfigRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    AppConfigRepository {

    companion object {
        private val APP_THEME = stringPreferencesKey("ez_card_theme")
    }

    override fun getAppTheme(): Flow<ThemeMode> {
        return dataStore.data.map { preferences ->
            val themeModeString = preferences[APP_THEME]
            // Use a safe call operator (?.) to handle null or unknown values
            val themeMode = ThemeMode.valueOf(themeModeString ?: ThemeMode.SYSTEM.toString())
            themeMode
        }
    }

    override suspend fun setAppTheme(themeMode: ThemeMode) {
        dataStore.edit { preferences ->
            preferences[APP_THEME] = themeMode.toString()
        }
    }
}

