package com.hamidrezabashiri.ezcard.ui.screens.changePassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class ChangePasswordViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val responseState = MutableStateFlow<ResponseState>(ResponseState.Idle)

    var password by mutableStateOf("")
        private set

    fun onPasswordChanged(new: String) {
        password = new
    }

    var newPassword by mutableStateOf("")
        private set

    fun onNewPasswordChanged(new: String) {
        newPassword = new
    }

    var passwordConfirmation by mutableStateOf("")
        private set

    fun onPasswordConfirmationChanged(newPassword: String) {
        passwordConfirmation = newPassword
    }


    fun onSignUpButtonClicked() {
        viewModelScope.launch {
            responseState.value = ResponseState.Loading


            if (password.isEmpty() || passwordConfirmation.isEmpty() || newPassword.isEmpty()) {
                responseState.value = ResponseState.Error("لطفا فیلد خالی را پر کنید")
                return@launch
            }

            if (newPassword != passwordConfirmation) {
                responseState.value = ResponseState.Error("پسورد یکسان نمی باشد")
                return@launch
            }
            val isOldPasswordCorrect =
                userRepository.loginUser(User(name = "admin", password = password))
            if (!isOldPasswordCorrect) {
                responseState.value = ResponseState.Error("پسورد فعلی صحیح نمی باشد")
                return@launch
            }

            val response = userRepository.signupUser(User(name = "admin", password = newPassword))
            if (response) {
                responseState.value = ResponseState.Success("رمز عبور با موفقیت تغییر یافت")

            } else {
                responseState.value = ResponseState.Error("password error")
            }
        }
    }


}