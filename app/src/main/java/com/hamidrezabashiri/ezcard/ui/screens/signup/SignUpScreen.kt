package com.hamidrezabashiri.ezcard.ui.screens.signup

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SignUpScreen(viewModel: SignUpViewModel = hiltViewModel(), onSignUpSuccess: () -> Unit) {

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

                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.hello_friend),
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )
                Text(
                    textAlign = TextAlign.Center,

                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.welcome),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp
                )
                Text(
                    textAlign = TextAlign.Center,

                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.create_your_password),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 20.sp
                )



                Row(Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                        text = stringResource(R.string.password), textAlign = TextAlign.Start
                    )
                }
                OutlinedTextField(isError = isErrorDisplayed && viewModel.password.isEmpty(),
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.password,
                    onValueChange = viewModel::onPasswordChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
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
                    keyboardActions = KeyboardActions(onDone = { viewModel.onSignUpButtonClicked() }),
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
                Row(Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                        text = stringResource(R.string.password_confirmation),
                        textAlign = TextAlign.Start
                    )
                }
                OutlinedTextField(isError = isErrorDisplayed && viewModel.password.isEmpty(),
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.passwordConfirmation,
                    onValueChange = viewModel::onPasswordConfirmationChanged,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray, cursorColor = Color.Gray
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    keyboardActions = KeyboardActions(onDone = { passwordConfirmationFocusRequester.requestFocus() }),
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
                if (isErrorDisplayed){
                    Text(text = "رمز ها یکسان نیستند!")
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
                        viewModel.onSignUpButtonClicked()
                    }) {

                    when (loginState) {
                        is LoginState.Success -> {
                            LaunchedEffect(Unit) {
                                onSignUpSuccess.invoke()
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