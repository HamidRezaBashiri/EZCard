package com.hamidrezabashiri.ezcard.ui.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.utils.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val dataStore: DataStore<Preferences>) :
    ViewModel() {

    private val isFirstLoginKey = booleanPreferencesKey("is_first_login")

    val loginState = MutableStateFlow<LoginState>(LoginState.Idle)

    var password by mutableStateOf("")
        private set

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    var passwordConfirmation by mutableStateOf("")
        private set

    fun onPasswordConfirmationChanged(newPassword: String) {
        passwordConfirmation = newPassword
    }


    fun onSignUpButtonClicked() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[isFirstLoginKey] = false
            }
        }
    }

}