package com.hamidrezabashiri.ezcard.ui.screens.aboutUs

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamidrezabashiri.ezcard.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUsScreen(upPress: () -> Unit) {
    val context = LocalContext.current

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
                text = stringResource(com.hamidrezabashiri.ezcard.R.string.about_us),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Icon(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(com.hamidrezabashiri.ezcard.R.drawable.logo_dark),
                contentDescription = " app logo",
                tint = Color.Unspecified
            )
            IconButton(
                onClick = upPress, modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .size(48.dp)
            ) {
                val layoutDirection = LocalLayoutDirection.current
                val rotationAngle = if (layoutDirection == LayoutDirection.Ltr) {
                    180f
                } else {
                    0f
                }
                Icon(
                    modifier = Modifier.rotate(rotationAngle),
                    imageVector = ImageVector.vectorResource(com.hamidrezabashiri.ezcard.R.drawable.chevron_left),
                    contentDescription = "back",
                    tint = Color.Unspecified
                )
            }
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, start = 16.dp, end = 16.dp),
                text = stringResource(R.string.about_us_thanks),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = stringResource(R.string.about_us_message),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp),
                text = stringResource(R.string.contacts_way),
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.primary
            )
            ContactRow(
                title = stringResource(com.hamidrezabashiri.ezcard.R.string.developer_about_us),
                email = "HamidRezaBashiri@proton.me"
            )
            ContactRow(
                title = stringResource(com.hamidrezabashiri.ezcard.R.string.ui_ux_designer_zohreh_bashiri),
                email = "HamidRezaBashiri@proton.me"
            )
            Icon(
                modifier = Modifier.weight(1f),
                imageVector = ImageVector.vectorResource(com.hamidrezabashiri.ezcard.R.drawable.logo_dark),
                contentDescription = "logo",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun ContactRow(title: String, email: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        IconButton(onClick = {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
            if (emailIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(emailIntent)
            } else {
                // Handle the case where no email app is available
                // You can show a toast or display an error message
            }
        }) {
            Icon(
                imageVector = ImageVector.vectorResource(com.hamidrezabashiri.ezcard.R.drawable.gmail),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}
