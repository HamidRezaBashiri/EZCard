package com.hamidrezabashiri.ezcard.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamidrezabashiri.ezcard.ui.theme.Blue200Transparent

@Preview
@Composable
fun WelcomeScreen() {

    Box(Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .size(500.dp)
                .background(
                    color = Blue200Transparent,
                    CircleShape
                )
        ) {

        }

    }

}