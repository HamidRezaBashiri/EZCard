package com.hamidrezabashiri.ezcard.ui.screens.changePassword

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.theme.ButtonCornerRoundedSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonHeightSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonTextSize
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250
import com.hamidrezabashiri.ezcard.ui.theme.HeadLineTextSize
import com.hamidrezabashiri.ezcard.ui.theme.OutlinedTextFieldTitleTextSize
import com.hamidrezabashiri.ezcard.utils.ResponseState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    viewModel: ChangePasswordViewModel = hiltViewModel(), upPress: () -> Unit
) {

    val responseState by viewModel.responseState.collectAsState()

    var passwordVisibility by remember { mutableStateOf(false) }

    val passwordFocusRequester = remember { FocusRequester() }
    val newPasswordFocusRequester = remember { FocusRequester() }
    val passwordConfirmationFocusRequester = remember { FocusRequester() }

    var isErrorDisplayed by remember { mutableStateOf(false) }
    var isToastDisplayed by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Scaffold(topBar = {

        Box(
            Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 8.dp),
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
                text = stringResource(R.string.change_password),
                fontSize = HeadLineTextSize,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(R.drawable.logo_dark),
                contentDescription = " app logo",
                tint = Color.Unspecified
            )

            IconButton(
                onClick = { upPress.invoke() },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            ) {
                val layoutDirection = LocalLayoutDirection.current

                // Determine the rotation angle based on the layout direction
                val rotationAngle = if (layoutDirection == LayoutDirection.Ltr) {
                    180f
                } else {
                    0f // No rotation for RTL
                }
                Icon(
                    modifier = Modifier.rotate(rotationAngle),
                    imageVector = ImageVector.vectorResource(R.drawable.chevron_left),
                    contentDescription = "back",
                    tint = Color.Unspecified
                )
            }
        }
    }) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 20.dp, bottom = 4.dp),
                        text = stringResource(R.string.old_pass),
                        textAlign = TextAlign.Start, fontSize = OutlinedTextFieldTitleTextSize
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { newPasswordFocusRequester.requestFocus() }),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = if (passwordVisibility) ImageVector.vectorResource(
                                    R.drawable.visible_pass
                                ) else ImageVector.vectorResource(
                                    R.drawable.invisible_pass
                                ),
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                            )
                        }
                    })
                Row(Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier.padding(start = 20.dp, bottom = 4.dp),
                        text = stringResource(R.string.new_password),
                        textAlign = TextAlign.Start, fontSize = OutlinedTextFieldTitleTextSize
                    )
                }
                OutlinedTextField(isError = isErrorDisplayed && viewModel.passwordConfirmation.isEmpty(),
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.newPassword,
                    onValueChange = viewModel::onNewPasswordChanged,
                    modifier = Modifier
                        .padding(horizontal = 16.dp).padding(bottom = 8.dp)
                        .fillMaxWidth()
                        .focusRequester(newPasswordFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray,
                        cursorColor = Color.Gray
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { passwordConfirmationFocusRequester.requestFocus() }),
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                imageVector = if (passwordVisibility) ImageVector.vectorResource(
                                    R.drawable.visible_pass
                                ) else ImageVector.vectorResource(
                                    R.drawable.invisible_pass
                                ),
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                            )
                        }
                    })

                Row(Modifier.fillMaxWidth()) {

                    Text(
                        modifier = Modifier.padding(start = 20.dp, bottom = 4.dp),
                        text = stringResource(R.string.password_confirmation),
                        textAlign = TextAlign.Start, fontSize = OutlinedTextFieldTitleTextSize
                    )
                }
                OutlinedTextField(isError = isErrorDisplayed && viewModel.passwordConfirmation.isEmpty(),
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.passwordConfirmation,
                    onValueChange = viewModel::onPasswordConfirmationChanged,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                        .focusRequester(passwordConfirmationFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray,
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
                                imageVector = if (passwordVisibility) ImageVector.vectorResource(
                                    R.drawable.visible_pass
                                ) else ImageVector.vectorResource(
                                    R.drawable.invisible_pass
                                ),
                                contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                            )
                        }
                    })
                if (isErrorDisplayed && viewModel.passwordConfirmation != viewModel.newPassword) {
                    Text(text = "رمز ها یکسان نیستند!", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ), modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(DarkBlue150, DarkBlue250),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite,
                        ), shape = RoundedCornerShape(ButtonCornerRoundedSize)
                    )
                    .fillMaxWidth()
                    .height(ButtonHeightSize), onClick = {
                    isErrorDisplayed = true
                    viewModel.onSignUpButtonClicked()
                }) {

                    when (responseState) {
                        is ResponseState.Success -> {
                            LaunchedEffect(Unit) {
                                upPress.invoke()
                                Toast.makeText(
                                    context,
                                    (responseState as ResponseState.Success).token,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            Text(
                                stringResource(R.string.confirm), fontSize = ButtonTextSize
                            )
                        }

                        is ResponseState.Error -> {
                            Text(
                                stringResource(R.string.confirm), fontSize = ButtonTextSize
                            )

                            LaunchedEffect(isToastDisplayed) {
                                isToastDisplayed = true
                            }
                            if (!isToastDisplayed) {
                                Toast.makeText(
                                    LocalContext.current,
                                    (responseState as ResponseState.Error).errorMessage,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        is ResponseState.Loading -> {
                            CircularProgressIndicator(
                                color = Color.White, modifier = Modifier
                                    .height(32.dp)
                                    .width(32.dp)

                            )
                        }

                        else -> {
                            Text(
                                stringResource(R.string.confirm), fontSize = ButtonTextSize
                            )
                        }
                    }

                }
                Button(shape = RoundedCornerShape(ButtonCornerRoundedSize),
                    border = BorderStroke(
                        1.dp, MaterialTheme.colorScheme.primary
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    modifier = Modifier
                        .padding(bottom = 32.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(ButtonHeightSize),
                    onClick = {
                        upPress.invoke()
                    }) {

                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = ButtonTextSize
                    )

                }


            }


        }
    }
}