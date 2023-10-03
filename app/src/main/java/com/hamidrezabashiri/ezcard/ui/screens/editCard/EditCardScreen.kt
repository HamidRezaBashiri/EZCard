package com.hamidrezabashiri.ezcard.ui.screens.editCard

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.common.drawVerticalScrollbar
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.ButtonTextSize
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue200
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250
import com.hamidrezabashiri.ezcard.ui.theme.HeadLineTextSize
import com.hamidrezabashiri.ezcard.ui.theme.OutlinedTextFieldTitleTextSize
import com.hamidrezabashiri.ezcard.utils.ResponseState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardScreen(
    viewModel: EditCardViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    cardId: Int?
) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val creditCard = viewModel.card.value

    LaunchedEffect(Unit) {
        if (cardId != null) {
            viewModel.getCardById(cardId)
        }
    }

//    val creditCard by remember { mutableStateOf(CreditCard()) }
    creditCard.cardHolderName = viewModel.fullName
    creditCard.cardNumber = viewModel.cardNumber
    creditCard.iban = when {
        viewModel.iban.isEmpty() -> "IR"
        !viewModel.iban.startsWith("IR") -> "IR${viewModel.iban}"
        else -> viewModel.iban
    }
    creditCard.accountNumber = viewModel.accountNumber
    creditCard.cvv2 = viewModel.cvv2
    val year = viewModel.dateYear
    val month = viewModel.dateMonth

    val expirationDate = when {
        year.isEmpty() && month.isEmpty() -> ""
        year.isEmpty() -> "/$month"
        month.isEmpty() -> "$year/"
        else -> "$year/$month"
    }

    creditCard.expirationDate = expirationDate
    creditCard.bankName = viewModel.bankNameDetector.detectBank(creditCard.cardNumber)


    var isErrorDisplayed by remember { mutableStateOf(false) }
    val loginState by viewModel.responseState.collectAsState()


    val nameFocusRequester = remember { FocusRequester() }
    val cardNumFocusRequester = remember { FocusRequester() }
    val ibanFocusRequester = remember { FocusRequester() }
    val accountFocusRequester = remember { FocusRequester() }
    val dateYearFocusRequester = remember { FocusRequester() }
    val dateMonthFocusRequester = remember { FocusRequester() }
    val cvvFocusRequester = remember { FocusRequester() }

    val scrollState = rememberScrollState()
    val interactionSource = remember { MutableInteractionSource() }





    Box(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    // Create a BlurEffect with a horizontal radius of 10f, vertical radius of 5f,
                    // and using CLAMP edge treatment.
                    renderEffect = BlurEffect(
                        radiusX = 60f,
                        radiusY = 30f,
                        edgeTreatment = TileMode.Clamp
                    )
                )
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Box(
                    Modifier
                        .size(430.dp)
                        .offset((-40).dp, (-240).dp)
                        .background(
                            color = Blue200Transparent,
                            CircleShape
                        )
                )
                Box(
                    Modifier
                        .size(320.dp)
                        .offset((100).dp, (-180).dp)
                        .background(
                            color = Blue200Transparent,
                            CircleShape
                        )

                )

            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {

            CardItem(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
                card = creditCard,
                onDeleteClicked = { /*TODO*/ },
                onCopyToClipBoard = { string ->
                    clipboardManager.setText(
                        AnnotatedString(
                            string
                        )
                    )
                }, isEditable = false
            )

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(R.string.edit_card),
                color = MaterialTheme.colorScheme.primary,
                fontSize = HeadLineTextSize,
                fontWeight = FontWeight.Medium
            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .drawVerticalScrollbar(scrollState)
                    .verticalScroll(state = scrollState)
            ) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                        text = stringResource(R.string.full_name),
                        textAlign = TextAlign.Start,
                        color = DarkBlue200, fontSize = OutlinedTextFieldTitleTextSize
                    )
                }

                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = stringResource(R.string.please_enter_card_owner_full_name),
                            fontSize = 14.sp
                        )
                    },

                    isError = isErrorDisplayed,
                    value = viewModel.fullName,
                    onValueChange = {
                        if (it.length <= 70) {
                            viewModel.onFullNameChanged(it)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                        .focusRequester(nameFocusRequester),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { cardNumFocusRequester.requestFocus() }),
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                        text = stringResource(R.string.card_number),
                        textAlign = TextAlign.Start,
                        color = DarkBlue200, fontSize = OutlinedTextFieldTitleTextSize
                    )
                }
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = stringResource(R.string.please_enter_card_number),
                            fontSize = 14.sp
                        )
                    },

                    isError = isErrorDisplayed,
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.cardNumber,
                    onValueChange = {
                        if (it.length <= 16) {
                            viewModel.onCardNumberChanged(it)
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .focusRequester(cardNumFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { ibanFocusRequester.requestFocus() }),
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                        text = stringResource(R.string.iban),
                        textAlign = TextAlign.Start,
                        color = DarkBlue200, fontSize = OutlinedTextFieldTitleTextSize
                    )
                }
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = stringResource(R.string.please_enter_your_iban_number),
                            fontSize = 14.sp
                        )
                    },

                    isError = isErrorDisplayed,
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.iban,
                    onValueChange = { newIban ->
                        if (newIban.length <= 26) {
                            viewModel.onIbanChanged(newIban)
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .focusRequester(ibanFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { accountFocusRequester.requestFocus() }),
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                        text = stringResource(R.string.account),
                        textAlign = TextAlign.Start,
                        color = DarkBlue200, fontSize = OutlinedTextFieldTitleTextSize
                    )
                }
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = stringResource(R.string.please_enter_your_account_number),
                            fontSize = 14.sp
                        )
                    },
                    isError = isErrorDisplayed,
                    shape = RoundedCornerShape(16.dp),
                    value = viewModel.accountNumber,
                    onValueChange = {
                        if (it.length <= 25) {
                            viewModel.onAccountNumberChanged(it)
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .focusRequester(accountFocusRequester),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                    ),
                    singleLine = true,
                    maxLines = 1,
                    interactionSource = remember { MutableInteractionSource() },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(onNext = { dateMonthFocusRequester.requestFocus() }),
                )

                Row(Modifier.fillMaxWidth()) {
                    Column {
                        Row(
                            Modifier
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                                text = stringResource(R.string.date),
                                textAlign = TextAlign.Start,
                                color = DarkBlue200, fontSize = OutlinedTextFieldTitleTextSize
                            )
                        }

                        Row {
                            OutlinedTextField(
                                placeholder = {
                                    Text(
                                        text = stringResource(R.string.month),
                                        fontSize = 14.sp
                                    )
                                },

                                isError = isErrorDisplayed,
                                shape = RoundedCornerShape(16.dp),
                                value = viewModel.dateMonth,
                                onValueChange = {
                                    if (it.length <= 2) {
                                        viewModel.onDateMonthChanged(it)
                                    }
                                },
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 8.dp)
                                    .width(84.dp)
                                    .focusRequester(dateMonthFocusRequester),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                                ),
                                singleLine = true,
                                maxLines = 1,
                                interactionSource = remember { MutableInteractionSource() },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(onNext = { dateYearFocusRequester.requestFocus() }),
                            )
                            OutlinedTextField(
                                placeholder = {
                                    Text(
                                        text = stringResource(R.string.year),
                                        fontSize = 14.sp
                                    )
                                },
                                isError = isErrorDisplayed,
                                shape = RoundedCornerShape(16.dp),
                                value = viewModel.dateYear,
                                onValueChange = {
                                    if (it.length <= 2) {
                                        viewModel.onDateYearChanged(it)
                                    }
                                },
                                modifier = Modifier
                                    .width(84.dp)
                                    .focusRequester(dateYearFocusRequester),
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                                ),
                                singleLine = true,
                                maxLines = 1,
                                interactionSource = remember { MutableInteractionSource() },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(onNext = { cvvFocusRequester.requestFocus() }),
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column {
                        Row(
                            Modifier
                                .padding(top = 16.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                                text = stringResource(R.string.cvv2),
                                textAlign = TextAlign.Start,
                                color = DarkBlue200, fontSize = OutlinedTextFieldTitleTextSize
                            )
                        }

                        OutlinedTextField(
                            placeholder = { Text(text = "cvv2", fontSize = 14.sp) },
                            isError = isErrorDisplayed,
                            shape = RoundedCornerShape(16.dp),
                            value = viewModel.cvv2,
                            onValueChange = {
                                if (it.length <= 5) {
                                    viewModel.onCvv2Changed(it)
                                }
                            },
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .width(84.dp)
                                .focusRequester(cvvFocusRequester),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                            ),
                            singleLine = true,
                            maxLines = 1,
                            interactionSource = remember { MutableInteractionSource() },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(onDone = {
                                viewModel.updateCard(
                                    creditCard
                                )
                                navigateUp.invoke()
                            })
                        )
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp)
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
                        viewModel.updateCard(creditCard)
                    }) {

                    when (loginState) {
                        is ResponseState.Success -> {
                            LaunchedEffect(Unit) {
                                navigateUp.invoke()
//                            TODO SHOW SUCCESS SCREEN AND NAVIGATE UP
                            }
                            Text(
                                stringResource(R.string.confirm),
                                textAlign = TextAlign.Center,
                                fontSize = ButtonTextSize, color = Color.White
                            )
                        }

                        is ResponseState.Error -> {
                            Text(
                                stringResource(R.string.confirm),
                                textAlign = TextAlign.Center,
                                fontSize = ButtonTextSize, color = Color.White
                            )

                            LaunchedEffect(isErrorDisplayed) {
                                isErrorDisplayed = true
                            }
                            if (!isErrorDisplayed) {
                                Toast.makeText(
                                    LocalContext.current,
                                    (loginState as ResponseState.Error).errorMessage,
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
                                stringResource(R.string.confirm),
                                textAlign = TextAlign.Center,
                                fontSize = ButtonTextSize, color = Color.White
                            )
                        }
                    }
                }
                Button(
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        1.dp, Brush.linearGradient(
                            colors = listOf(DarkBlue150, DarkBlue250),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite,
                        )
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                    ),
                    modifier = Modifier
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {
                        navigateUp.invoke()
                    }) {

                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        fontSize = ButtonTextSize
                    )

                }
            }


        }
    }
}

