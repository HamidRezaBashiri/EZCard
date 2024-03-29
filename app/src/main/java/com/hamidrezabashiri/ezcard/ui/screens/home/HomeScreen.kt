package com.hamidrezabashiri.ezcard.ui.screens.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.model.ThemeMode
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.common.drawVerticalScrollbar
import com.hamidrezabashiri.ezcard.ui.common.showToast
import com.hamidrezabashiri.ezcard.ui.navigation.MainDestinations
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.BodyTextSize
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.Grey100
import com.hamidrezabashiri.ezcard.ui.theme.Grey200
import com.hamidrezabashiri.ezcard.ui.theme.HeadLineTextSize
import com.hamidrezabashiri.ezcard.ui.theme.OutlinedTextFieldTitleTextSize
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToAddScreen: () -> Unit,
    navigateWithParam: (Int, NavBackStackEntry, String) -> Unit,
    navBackStackEntry: NavBackStackEntry,
) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val scrollState = rememberScrollState()


    val cardList by viewModel.cardListFlow.collectAsState(emptyList())
    val pagerState = rememberPagerState(pageCount = { cardList.size })

    val creditCard = cardList.getOrNull(pagerState.currentPage) ?: CreditCard()

    // Update ViewModel with credit card details
    viewModel.onFullNameChanged(creditCard.cardHolderName)
    viewModel.onCardNumberChanged(creditCard.cardNumber)
    viewModel.onIbanChanged(creditCard.iban)
    viewModel.onAccountNumberChanged(creditCard.accountNumber)
    viewModel.onDateYearChanged(creditCard.expirationDate)
    viewModel.onCvv2Changed(creditCard.cvv2)

    val isDarkTheme = when (viewModel.appThemeState.value) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        null -> isSystemInDarkTheme()
    }
    val backgroundVector =
        if (isDarkTheme) R.drawable.guy_holding_card_dark else R.drawable.guy_holding_card_illustration
    val fabVector =
        if (cardList.isEmpty()) Icons.Default.Add else ImageVector.vectorResource(id = R.drawable.icon__share_)

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                if (cardList.isEmpty()) {
                    navigateToAddScreen.invoke()
                } else {
                    cardList.getOrNull(pagerState.currentPage)?.id?.let { cardId ->
                        navigateWithParam(
                            cardId, navBackStackEntry, MainDestinations.SHARE_CARD_ROUTE
                        )
                    }
                }
            },
            containerColor = DarkBlue150,
        ) {
            Icon(
                imageVector = fabVector,
                contentDescription = "fab",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }, modifier = Modifier.fillMaxSize()) {

        Box {
            // Blurred Background
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .graphicsLayer(
                        renderEffect = BlurEffect(
                            radiusX = 60f, radiusY = 30f, edgeTreatment = TileMode.Clamp
                        )
                    )
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Box(
                        Modifier
                            .size(430.dp)
                            .offset((-40).dp, (-240).dp)
                            .background(
                                color = Blue200Transparent, CircleShape
                            )
                    )
                    Box(
                        Modifier
                            .size(320.dp)
                            .offset((100).dp, (-180).dp)
                            .background(
                                color = Blue200Transparent, CircleShape
                            )
                    )
                }
            }

            if (cardList.isEmpty()) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .size(130.dp)
                        .padding(top = 48.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.logo_dark),
                    contentDescription = "logo",
                    tint = Color.Unspecified
                )
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = ImageVector.vectorResource(backgroundVector),
                    contentDescription = "background",
                    tint = Color.Unspecified
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.there_is_no_card_to_show),
                        fontSize = HeadLineTextSize, fontWeight = FontWeight.Medium,
                        color = DarkBlue150
                    )
                    Text(
                        text = stringResource(R.string.to_add_new_card_press_plus_button),
                        color = Grey200, fontSize = BodyTextSize
                    )
                    Text(
                        text = stringResource(R.string.at_the_bottom_of_screen),
                        color = Grey200,
                        fontSize = BodyTextSize
                    )
                }
            } else {
                // Render card pager UI
                val screenWidthInPixels = with(LocalDensity.current) {
                    LocalConfiguration.current.screenWidthDp.dp
                }
                val cardWidth = (screenWidthInPixels * 0.9f)

                Column() {
                    HorizontalPager(
                        beyondBoundsPageCount = 1,
                        pageSize = PageSize.Fixed(cardWidth),
                        state = pagerState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        contentPadding = PaddingValues(end = 24.dp, start = 22.dp)
                    ) {
                        CardItem(
                            modifier = Modifier.width(cardWidth),
                            card = cardList[it],
                            onDeleteClicked = {
                                cardList[it].id?.let { cardId ->
                                    navigateWithParam(
                                        cardId,
                                        navBackStackEntry,
                                        MainDestinations.CONFIRM_DELETE_ROUTE
                                    )
                                }
                            },
                            onEditClicked = {
                                cardList[it].id?.let { cardId ->
                                    navigateWithParam(
                                        cardId,
                                        navBackStackEntry,
                                        MainDestinations.EDIT_CARD_ROUTE
                                    )
                                }
                            },
                            onCopyToClipBoard = { string ->
                                clipboardManager.setText(
                                    AnnotatedString(
                                        string
                                    )
                                )
                            }, isEditable = true
                        )
                    }

                    Row(
                        Modifier
                            .height(10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val maxAlpha = 1.0f
                            val minAlpha = 0f
                            val alphaDifference = maxAlpha - minAlpha
                            val distance = (pagerState.currentPage - iteration).absoluteValue
                            val alpha =
                                maxAlpha - (alphaDifference / pagerState.pageCount) * distance

                            var color =
                                if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                            if (isDarkTheme) {
                                color =
                                    if (pagerState.currentPage == iteration) Color.LightGray else Color.DarkGray
                            }
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color.copy(alpha))
                                    .size(5.dp)
                            )
                        }
                    }

                    Text(
                        text = stringResource(R.string.card_info),
                        fontSize = HeadLineTextSize,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        color = DarkBlue150
                    )

                    Column(
                        Modifier
                            .drawVerticalScrollbar(scrollState)
                            .verticalScroll(state = scrollState)
                            .padding(bottom = 64.dp)
                    ) {
                        RowWithCopyButton(label = stringResource(R.string.card_number),
                            text = viewModel.cardNumber,
                            onCopyClicked = {
                                showToast(context)
                                clipboardManager.setText(AnnotatedString(viewModel.cardNumber))
                            })

                        RowWithCopyButton(label = stringResource(R.string.full_name),
                            text = viewModel.fullName,
                            onCopyClicked = {
                                showToast(context)
                                clipboardManager.setText(AnnotatedString(viewModel.fullName))
                            })

                        RowWithCopyButton(label = stringResource(R.string.iban),
                            text = viewModel.iban,
                            onCopyClicked = {
                                showToast(context)
                                clipboardManager.setText(AnnotatedString(viewModel.iban))
                            })

                        RowWithCopyButton(label = stringResource(R.string.account),
                            text = viewModel.accountNumber,
                            onCopyClicked = {
                                showToast(context)
                                clipboardManager.setText(AnnotatedString(viewModel.accountNumber))
                            })

                        Row(Modifier.fillMaxWidth()) {
                            Column {
                                Row(
                                    Modifier.padding(top = 16.dp)
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                                        text = stringResource(R.string.date),
                                        textAlign = TextAlign.Start,
                                        color = DarkBlue150,
                                        fontSize = OutlinedTextFieldTitleTextSize
                                    )
                                }

                                OutlinedTextField(shape = RoundedCornerShape(16.dp),
                                    value = viewModel.dateYear,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 8.dp)
                                        .width(164.dp),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        unfocusedBorderColor = Grey200,
                                        focusedBorderColor = Grey200
                                    ),
                                    singleLine = true,
                                    maxLines = 1,
                                    readOnly = true,
                                    trailingIcon = {
                                        IconButton(onClick = {
                                            showToast(context)
                                            clipboardManager.setText(AnnotatedString(viewModel.dateYear))
                                        }) {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(R.drawable.copy),
                                                contentDescription = "copy button",
                                                tint = Grey100
                                            )
                                        }
                                    })
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Column {
                                Row(
                                    Modifier.padding(top = 16.dp)
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
                                        text = stringResource(R.string.cvv2),
                                        textAlign = TextAlign.Start,
                                        color = DarkBlue150,
                                        fontSize = OutlinedTextFieldTitleTextSize
                                    )
                                }

                                OutlinedTextField(placeholder = {
                                    Text(
                                        text = "cvv2", fontSize = 10.sp
                                    )
                                },
                                    shape = RoundedCornerShape(16.dp),
                                    value = viewModel.cvv2,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .width(120.dp),
                                    colors = TextFieldDefaults.outlinedTextFieldColors(
                                        unfocusedBorderColor = Grey200,
                                        focusedBorderColor = Grey200
                                    ),
                                    singleLine = true,
                                    maxLines = 1,
                                    readOnly = true,
                                    trailingIcon = {
                                        IconButton(onClick = {
                                            showToast(context)
                                            clipboardManager.setText(AnnotatedString(viewModel.cvv2))
                                        }) {
                                            Icon(
                                                imageVector = ImageVector.vectorResource(R.drawable.copy),
                                                contentDescription = "copy button",
                                                tint = Grey100
                                            )
                                        }
                                    })
                            }
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun showToast(context: Context) {
    Toast.makeText(context, "در کلیپ برد کپی شد!", Toast.LENGTH_SHORT).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowWithCopyButton(
    label: String, text: String, onCopyClicked: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 26.dp, bottom = 4.dp),
            text = label,
            textAlign = TextAlign.Start,
            color = DarkBlue150, fontSize = OutlinedTextFieldTitleTextSize
        )
    }

    OutlinedTextField(shape = RoundedCornerShape(16.dp),
        value = text,
        onValueChange = {},
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Grey200,
            focusedBorderColor = Grey200
        ),
        singleLine = true,
        maxLines = 1,
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = onCopyClicked) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.copy),
                    contentDescription = "copy button",
                    tint = Grey100
                )
            }
        })
}


