package com.hamidrezabashiri.ezcard.ui.screens.changePassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamidrezabashiri.ezcard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen() {

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
                text = stringResource(R.string.settings),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
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
                onClick = { /*TODO*/ }, modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.chevron_left),
                    contentDescription = "back",
                    tint = Color.Unspecified
                )
            }
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

        }
    }
}