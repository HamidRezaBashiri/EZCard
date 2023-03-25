package com.hamidrezabashiri.ezcard.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamidrezabashiri.ezcard.ui.theme.MyButtonShape


@Preview
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit = {}, viewModel: LoginViewModel = LoginViewModel()) {
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = { /* Handle login action */ }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp , horizontal = 36.dp)
        )

        Button(
            shape = MyButtonShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            onClick = {
                if (password == "myPassword") {
                    onLoginSuccess()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .layoutId("loginButton")
                .height(48.dp)
                .padding(36.dp, 0.dp, 36.dp, 0.dp)
        ) {
            Text("Login")
        }
    }
}
