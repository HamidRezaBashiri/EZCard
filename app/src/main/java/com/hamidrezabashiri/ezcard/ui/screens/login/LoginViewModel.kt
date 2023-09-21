package com.hamidrezabashiri.ezcard.ui.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class LoginViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val loginState = MutableStateFlow<LoginState>(LoginState.Idle)

    var password by mutableStateOf("")
        private set

    fun onPasswordChanged(newPassword: String) {
        password = newPassword
    }

    fun onLoginClicked() {

        viewModelScope.launch {
            loginState.value = LoginState.Loading
            if (password.isEmpty()) {
                loginState.value = LoginState.Error("لطفا پسورد را وارد کنید!")
                return@launch
            }
            val response = userRepository.loginUser(User(password = password))
            if (response) {
                loginState.value = LoginState.Success("")
            } else {
                loginState.value = LoginState.Error("کلمه عبور صحیح نمی باشد!")
            }

        }

    }
}