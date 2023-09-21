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
import com.hamidrezabashiri.ezcard.data.dataModel.User
import com.hamidrezabashiri.ezcard.data.repository.user.UserRepository
import com.hamidrezabashiri.ezcard.utils.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userRepository: UserRepository
) :
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
            loginState.value = LoginState.Loading


            if (password.isEmpty() || passwordConfirmation.isEmpty()) {
                loginState.value = LoginState.Error("لطفا پسورد را وارد کنید")
                return@launch
            }

            if (password != passwordConfirmation) {
                loginState.value = LoginState.Error("پسورد یکسان نمی باشد")
                return@launch
            }

            val response = userRepository.signupUser(User(name = "admin", password = password))
            if (response) {
                loginState.value = LoginState.Success("")
                dataStore.edit { preferences ->
                    preferences[isFirstLoginKey] = false
                }
            } else {
                loginState.value = LoginState.Error("password error")
            }
        }
    }


}