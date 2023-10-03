package com.hamidrezabashiri.ezcard.ui.screens.wallet

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.model.ThemeMode
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.common.drawVerticalScrollbar
import com.hamidrezabashiri.ezcard.ui.navigation.MainDestinations
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.BodyTextSize
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.Grey200
import com.hamidrezabashiri.ezcard.ui.theme.HeadLineTextSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    viewModel: WalletViewModel = hiltViewModel(),
    navigateToAddScreen: () -> Unit,
    navigateWithParam: (Int, NavBackStackEntry, String) -> Unit,
    navBackStackEntry: NavBackStackEntry,
) {
    val cardList by viewModel.cardListFlow.collectAsState(emptyList())
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val lazyListState = rememberLazyListState()

    val isDarkTheme = when (viewModel.appThemeState.value) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        null -> isSystemInDarkTheme()
    }
    val backgroundVector =
        if (isDarkTheme) R.drawable.guy_holding_card_dark else R.drawable.guy_holding_card_illustration


    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {

                navigateToAddScreen.invoke()
            },
            containerColor = DarkBlue150,

            ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "fab",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }

    }, modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
//        Blurred Background
            Box(
                modifier = Modifier
                    .padding(it)
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
            }

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .drawVerticalScrollbar(lazyListState)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {

                items(cardList) { card ->
                    CardItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        card = card,
                        onDeleteClicked = {
                            card.id?.let { it1 ->
                                navigateWithParam(
                                    it1, navBackStackEntry,
                                    MainDestinations.CONFIRM_DELETE_ROUTE
                                )
                            }
                        }, onEditClicked = {
                            card.id?.let { cardId ->
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
            }
        }
    }
}