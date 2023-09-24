package com.hamidrezabashiri.ezcard.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250
import com.hamidrezabashiri.ezcard.utils.LoginState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {


    val loginState by viewModel.loginState.collectAsState()

    var passwordVisibility by remember { mutableStateOf(false) }
    val passwordFocusRequester = remember { FocusRequester() }
    val passwordConfirmationFocusRequester = remember { FocusRequester() }

    var isErrorDisplayed by remember { mutableStateOf(false) }
    var isToastDisplayed by remember {
        mutableStateOf(false)
    }
    Box(Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-30).dp),
            imageVector = ImageVector.vectorResource(R.drawable.background_login),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds
        )

        Column(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(vertical = 64.dp)
                        .size(180.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.logo_dark),
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .weight(2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(.5f))

                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.hello_friend),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp, color = MaterialTheme.colorScheme.primary
                )
                Text(
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = stringResource(R.string.welcome),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 26.sp
                )



                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp, bottom = 4.dp),
                        text = "کلمه عبور خود را وارد کنید", textAlign = TextAlign.Start
                    )
                }
                OutlinedTextField(isError = isErrorDisplayed && viewModel.password.isEmpty(),
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.password,
                    onValueChange = viewModel::onPasswordChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                        .focusRequester(passwordFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Gray,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        cursorColor = Color.Gray
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(onDone = { viewModel.onLoginClicked() }),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = if (passwordVisibility) ImageVector.vectorResource(R.drawable.visible_pass) else ImageVector.vectorResource(
                                    R.drawable.invisible_pass
                                ),
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                            )
                        }
                    })

                if (isErrorDisplayed && isToastDisplayed) {
                    Text(text = "کلمه عبور صحیح نیست!", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(DarkBlue150, DarkBlue250),
                                start = Offset(0f, 0f),
                                end = Offset.Infinite,
                            ), shape = RoundedCornerShape(16.dp)
                        )
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {
                        viewModel.onLoginClicked()
                        isToastDisplayed = false
                        isErrorDisplayed = true
                    }) {

                    when (loginState) {
                        is LoginState.Success -> {
                            LaunchedEffect(Unit) {
                                onLoginSuccess.invoke()
                            }
                            Text(stringResource(R.string.login))
                        }

                        is LoginState.Error -> {
                            Text(stringResource(R.string.login))
                            LaunchedEffect(isToastDisplayed) {
                                isToastDisplayed = true
                            }
                            if (!isToastDisplayed) {
                                Toast.makeText(
                                    LocalContext.current,
                                    (loginState as LoginState.Error).errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        is LoginState.Loading -> {
                            CircularProgressIndicator(
                                color = Color.White, modifier = Modifier
                                    .height(32.dp)
                                    .width(32.dp)

                            )
                        }

                        else -> {
                            Text("ورود")
                        }
                    }

//                Text(text = stringResource(R.string.login))
                }


            }

        }
    }
}


