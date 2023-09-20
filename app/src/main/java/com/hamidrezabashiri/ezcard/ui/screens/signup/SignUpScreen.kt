package com.hamidrezabashiri.ezcard.ui.screens.signup

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), onSignUpSuccess: () -> Unit) {

    Text(text = "signup")
    Button(onClick = {
        viewModel.onSignUpButtonClicked()
        onSignUpSuccess.invoke() }) {

    }
}