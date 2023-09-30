package com.hamidrezabashiri.ezcard.ui.screens.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.model.ThemeMode
import com.hamidrezabashiri.ezcard.ui.common.EzCardThemeSwitch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onThemeChange: () -> Unit,
    navigateToChangePassword: () -> Unit,
    navigateToAboutUs: () -> Unit
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()

    var isDarkTheme by remember {
        mutableStateOf(isSystemInDarkTheme)
    }

    val themeMode = viewModel.appThemeState.value


    when (themeMode) {
        ThemeMode.DARK -> isDarkTheme = true
        ThemeMode.LIGHT -> isDarkTheme = false
        else -> {}
    }

    Scaffold(Modifier.fillMaxSize(), topBar = {


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
                .fillMaxSize()
                .padding(it)
        ) {
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp),
                1.dp,
                Color.LightGray
            )
            Button(
                onClick = { navigateToChangePassword.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.change_password),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp

                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), 1.dp, Color.LightGray
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.chose_language),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp

                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.arrow_down),
                    contentDescription = "arrow down"
                )

            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), 1.dp, Color.LightGray
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.theme),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp

                )
                Spacer(modifier = Modifier.weight(1f))
                EzCardThemeSwitch(checked = isDarkTheme) { isDarkTheme ->
                    if (isDarkTheme) viewModel.changeTheme(ThemeMode.DARK) else viewModel.changeTheme(
                        ThemeMode.LIGHT
                    )
                    onThemeChange.invoke()
                }
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), 1.dp, Color.LightGray
            )

            Button(
                onClick = { navigateToAboutUs.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text(
                    text = stringResource(R.string.about_us),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp
                )
            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), 1.dp, Color.LightGray
            )


        }

    }
}