package com.hamidrezabashiri.ezcard.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(dataStore: DataStore<Preferences>) : ViewModel() {

    private val isFirstLoginKey = booleanPreferencesKey("is_first_login")

    val isFirstLogin: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[isFirstLoginKey] ?: true
    }


}