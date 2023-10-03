package com.hamidrezabashiri.ezcard.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent
import com.hamidrezabashiri.ezcard.ui.theme.ButtonCornerRoundedSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonHeightSize
import com.hamidrezabashiri.ezcard.ui.theme.ButtonTextSize
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel(),
    onLogin: () -> Unit,
    isDarkTheme: Boolean
) {
//    val isDarkTheme = isSystemInDarkTheme()

    val backgroundVector =
        if (isDarkTheme) R.drawable.background_dark_guy else R.drawable.background_guy

    Box(Modifier.fillMaxSize()) {

        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {

            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-80).dp),
                imageVector = ImageVector.vectorResource(backgroundVector),
                contentDescription = ""
            )

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

            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(vertical = 64.dp)
                    .size(107.dp),
                imageVector = ImageVector.vectorResource(R.drawable.logo_dark),
                contentDescription = ""
            )
        }



        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                textAlign = TextAlign.Center,
//                modifier= Modifier.padding(16.dp),
                text = stringResource(R.string.hello_friend),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.welcome_to_ezcard),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp
            )
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.welcome_note), fontSize = 16.sp
            )


            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .padding(horizontal = 32.dp, vertical = 32.dp)
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
                    onLogin.invoke()
                }) {
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.login),
                    fontSize = ButtonTextSize, color = Color.White
                )
            }


        }


    }


}
