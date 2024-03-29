package com.hamidrezabashiri.ezcard.ui.screens.shareCard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.BodyTextSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonCornerRoundedSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonHeightSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonTextSize
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250
import com.hamidrezabashiri.ezcard.ui.theme.HeadLineTextSize
import kotlinx.coroutines.delay

@Composable
fun ShareCardScreen(
    viewModel: ShareCardViewModel = hiltViewModel(),
    cardId: Int?,
    upPress: () -> Unit,
) {
    var isSuccessAnimationPlaying by remember {
        mutableStateOf(false)
    }
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current // If you're in a Composable

    val card = viewModel.card

    val checkboxStates = viewModel.checkboxStates

//    val checkboxTexts = viewModel.checkboxTexts
    val checkboxTexts = listOf(
        context.getString(R.string.card_holder_name),
        context.getString(R.string.card_number),
        context.getString(R.string.iban),
        context.getString(R.string.account),
//        context.getString(R.string.expire_date),
//        context.getString(R.string.cvv2),
    )


    LaunchedEffect(Unit) {
        if (cardId != null) {
            viewModel.getCardById(cardId)
        }
    }


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
                .padding(top = 16.dp)
        ) {

            CardItem(
                modifier = Modifier.padding(horizontal = 8.dp),
                card = card.value,
                onDeleteClicked = { /*TODO*/ },
                onCopyToClipBoard = { string ->
                    clipboardManager.setText(
                        AnnotatedString(
                            string
                        )
                    )
                }, isEditable = false
            )

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_card_send),
                contentDescription = "share icon",
                modifier = Modifier.padding(top = 16.dp),
                tint = Color.Unspecified
            )

            Text(
                text = "اشتراک گذاری",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = HeadLineTextSize, color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = stringResource(R.string.share_card_message),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Start,
                fontSize = BodyTextSize,
            )

            checkboxTexts.forEachIndexed { index, text ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = DarkBlue150
                        ),
                        checked = checkboxStates[index],
                        onCheckedChange = { isChecked ->
                            checkboxStates[index] = isChecked
                        },
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(text, color = DarkBlue150)

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
                        ), shape = RoundedCornerShape(ButtonCornerRoundedSize)
                    )
                    .fillMaxWidth()
                    .height(ButtonHeightSize),
                onClick = {
                    isSuccessAnimationPlaying = true
                    viewModel.generateAndShareMessage(context)
                }) {

                Text(
                    stringResource(R.string.send),
                    textAlign = TextAlign.Center,
                    fontSize = ButtonTextSize, color = Color.White
                )
            }
            Button(
                shape = RoundedCornerShape(ButtonCornerRoundedSize),
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
                    .height(ButtonHeightSize),
                onClick = {
                    upPress.invoke()
                }) {

                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontSize = ButtonTextSize
                )

            }


        }

        if (isSuccessAnimationPlaying) {
            Box(Modifier.fillMaxSize()) {


                val composition by rememberLottieComposition(

                    LottieCompositionSpec
                        // here `code` is the file name of lottie file
                        // use it accordingly
                        .RawRes(R.raw.animation_2)
                )

                // to control the animation
                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    composition = composition,
                    iterations = 1,
                )
                LaunchedEffect(key1 = Unit, block = {
                    delay(2500)
                    isSuccessAnimationPlaying = false
                })
            }
        }
    }


}