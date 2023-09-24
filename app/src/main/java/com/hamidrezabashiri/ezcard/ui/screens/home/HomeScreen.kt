package com.hamidrezabashiri.ezcard.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.Grey200


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    isDarkTheme: Boolean,
    navigateToAddScreen: () -> Unit,
    navigateToShareScreen: () -> Unit,
) {

    val cardList by viewModel.cardListFlow.collectAsState(emptyList())

    val clipboardManager: ClipboardManager = LocalClipboardManager.current

//    val cardList = listOf<CreditCard>(CreditCard(),CreditCard(),CreditCard())


    val backgroundVector =
        if (isDarkTheme) R.drawable.guy_holding_card_dark else R.drawable.guy_holding_card_illustration

    val fabVector =
        if (cardList.isEmpty()) Icons.Default.Add else ImageVector.vectorResource(id = R.drawable.icon__share_)


    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = DarkBlue150,
//            modifier = Modifier.background(
//                Brush.linearGradient(
//                    colors = listOf(DarkBlue150, DarkBlue250),
//                    start = Offset(0f, 0f),
//                    end = Offset.Infinite,
//                )
//            )
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
                    contentDescription = "logo", tint = Color.Unspecified
                )
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = ImageVector.vectorResource(backgroundVector),
                    contentDescription = "background", tint = Color.Unspecified
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.there_is_no_card_to_show),
                        fontSize = 24.sp,
                        color = DarkBlue150
                    )
                    Text(
                        text = stringResource(R.string.to_add_new_card_press_plus_button),
                        color = Grey200
                    )
                    Text(text = stringResource(R.string.at_the_bottom_of_screen), color = Grey200)

                }
            } else {
                LazyRow {
                    items(cardList) { card ->

                        CardItem(
                            card,
                            onDeleteClicked = { viewModel.onDeleteCard(card) },
                            onCopyToClipBoard = { string ->
                                clipboardManager.setText(
                                    AnnotatedString(
                                        string
                                    )
                                )
                            })

                    }
                }
            }


        }

    }

}