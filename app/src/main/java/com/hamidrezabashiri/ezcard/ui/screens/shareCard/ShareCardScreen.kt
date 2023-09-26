package com.hamidrezabashiri.ezcard.ui.screens.shareCard

import android.content.Intent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250

@Composable
fun ShareCardScreen(
    viewModel: ShareCardViewModel = hiltViewModel(),
    cardId: Int?,
    upPress: () -> Unit,
) {

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val context = LocalContext.current // If you're in a Composable

    val card = viewModel.card

    val checkboxStates = viewModel.checkboxStates
    val checkboxTexts = viewModel.checkboxTexts

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
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {

            CardItem(
                card = card.value,
                onDeleteClicked = { /*TODO*/ },
                onCopyToClipBoard = { string ->
                    clipboardManager.setText(
                        AnnotatedString(
                            string
                        )
                    )
                })

            Text(
                text = "اطلاعاتی که می خواهید به اشتراک بگذارید:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Start,
                fontSize = 24.sp
            )

            checkboxTexts.forEachIndexed { index, text ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkboxStates[index],
                        onCheckedChange = { isChecked ->
                            checkboxStates[index] = isChecked
                        },
                        modifier = Modifier.padding(0.dp)
                    )
                    Text(text)

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
                    viewModel.generateAndShareMessage(context)
                }) {

                Text(
                    stringResource(R.string.send),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )
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
                    upPress.invoke()
                }) {

                Text(
                    text = stringResource(id = R.string.cancel),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                )

            }


        }
    }

}