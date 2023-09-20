package com.hamidrezabashiri.ezcard.ui.screens

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(dataStore: DataStore<Preferences>) : ViewModel() {

    private val isFirstLoginKey = booleanPreferencesKey("is_first_login")

    val isFirstLogin: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[isFirstLoginKey] ?: true
    }
//


}