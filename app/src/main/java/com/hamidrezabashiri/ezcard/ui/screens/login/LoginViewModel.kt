package com.hamidrezabashiri.ezcard.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamidrezabashiri.ezcard.data.dataModel.User
import com.hamidrezabashiri.ezcard.data.repository.user.UserRepository
import com.hamidrezabashiri.ezcard.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    dataStore: DataStore<Preferences>,
) : ViewModel() {

    private val isFirstLoginKey = booleanPreferencesKey("is_first_login")

    val isFirstLogin: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[isFirstLoginKey] ?: true
    }
    val responseState = MutableStateFlow<ResponseState>(ResponseState.Idle)

    var password by mutableStateOf("")
        private set

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun onLoginClicked() {

        viewModelScope.launch {
            responseState.value = ResponseState.Loading
            if (password.isEmpty()) {
                responseState.value = ResponseState.Error("لطفا پسورد را وارد کنید!")
                return@launch
            }
            val response = userRepository.loginUser(User(password = password))
            if (response) {
                responseState.value = ResponseState.Success("")
            } else {
                responseState.value = ResponseState.Error("کلمه عبور صحیح نمی باشد!")
            }

        }

    }
}