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
import com.hamidrezabashiri.ezcard.utils.ResponseState
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

    val responseState = MutableStateFlow<ResponseState>(ResponseState.Idle)

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
            responseState.value = ResponseState.Loading


            if (password.isEmpty() || passwordConfirmation.isEmpty()) {
                responseState.value = ResponseState.Error("لطفا پسورد را وارد کنید")
                return@launch
            }

            if (password != passwordConfirmation) {
                responseState.value = ResponseState.Error("پسورد یکسان نمی باشد")
                return@launch
            }

            val response = userRepository.signupUser(User(name = "admin", password = password))
            if (response) {
                responseState.value = ResponseState.Success("")
                dataStore.edit { preferences ->
                    preferences[isFirstLoginKey] = false
                }
            } else {
                responseState.value = ResponseState.Error("password error")
            }
        }
    }


}