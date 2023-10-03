package com.hamidrezabashiri.ezcard.ui.screens.confirmDelete

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Brush
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.common.CardItem
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.ButtonCornerRoundedSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonHeightSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonTextSize
import com.hamidrezabashiri.ezcard.ui.theme.HeadLineTextSize
import com.hamidrezabashiri.ezcard.ui.theme.Red500


@Composable
fun ConfirmDeleteScreen(
    viewModel: ConfirmDeleteViewModel = hiltViewModel(),
    upPress: () -> Unit,
    cardId: Int?,
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val card = viewModel.card

    LaunchedEffect(Unit) {
        if (cardId != null) {
            viewModel.getCardById(cardId)
        }
    }

    Box {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
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
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { upPress.invoke() }, modifier = Modifier
                        .padding(top = 16.dp)
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
            Image(
                modifier = Modifier
                    .size(107.dp),
                imageVector = ImageVector.vectorResource(R.drawable.logo_dark),
                contentDescription = "",
                alignment = Alignment.TopCenter
            )

            CardItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), card = card.value,
                onDeleteClicked = { },
                onCopyToClipBoard = { string ->
                    clipboardManager.setText(
                        AnnotatedString(
                            string
                        )
                    )
                }, isEditable = false
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.do_you_want_to_delete_above_card),
                fontSize = HeadLineTextSize,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                lineHeight = 36.sp, fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp, top = 16.dp)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(MaterialTheme.colorScheme.error, Red500),
                            start = Offset(0f, 0f),
                            end = Offset.Infinite,
                        ), shape = RoundedCornerShape(ButtonCornerRoundedSize)
                    )
                    .fillMaxWidth()
                    .height(ButtonHeightSize),
                onClick = {
                    viewModel.onDeleteCard(card.value)
                    upPress.invoke()
                }) {

                Text(
                    text = stringResource(id = R.string.confirm),
                    color = Color.White, fontSize = ButtonTextSize

                )
            }
            Button(
                shape = RoundedCornerShape(ButtonCornerRoundedSize),
                border = BorderStroke(
                    1.dp, MaterialTheme.colorScheme.primary
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
                    color = MaterialTheme.colorScheme.primary, fontSize = ButtonTextSize
                )

            }
        }


    }

}

