package com.hamidrezabashiri.ezcard.ui.common

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.ui.theme.TurquoiseDark
import com.hamidrezabashiri.ezcard.ui.theme.pink200

@Composable
fun CardItem(card: CreditCard, onDeleteClicked: () -> Unit, onCopyToClipBoard: (String) -> Unit) {

    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(0.8f)
            .height(250.dp)
            .background(
                shape = RoundedCornerShape(32.dp),
                brush = Brush.linearGradient(0.0f to pink200, 1.0f to TurquoiseDark)
            )
            .padding(16.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {

                IconButton(onClick = { onDeleteClicked.invoke() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.trash),
                        contentDescription = "delete btn"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(card.bankLogoResId),
                    contentDescription = "bank logo"
                )

            }

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = card.cardNumber)
                IconButton(onClick = { onCopyToClipBoard.invoke(card.cardNumber) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.copy),
                        contentDescription = "copy button",
                        tint = Color.Unspecified
                    )
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = card.iban)
                IconButton(onClick = { onCopyToClipBoard.invoke(card.iban) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.copy),
                        contentDescription = "copy button",
                        tint = Color.Unspecified
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {
                Row(verticalAlignment = Alignment.Bottom) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "تاریخ انقضا", color = Color.White, fontSize = 12.sp)
                        Text(
                            text = card.expirationDate,
                            color = Color.White,
                            fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
                        )
                    }
                    IconButton(onClick = { onCopyToClipBoard.invoke(card.expirationDate) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.copy),
                            contentDescription = "copy button",
                            tint = Color.Unspecified
                        )
                    }

                }
                Spacer(modifier = Modifier.weight(1f))

                Row(verticalAlignment = Alignment.Bottom) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "CVV2", color = Color.White, fontSize = 12.sp)
                        Text(
                            text = card.cvv2,
                            color = Color.White,
                            fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
                        )
                    }
                    IconButton(onClick = { onCopyToClipBoard.invoke(card.cvv2) }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.copy),
                            contentDescription = "copy button",
                            tint = Color.Unspecified
                        )
                    }

                }
            }

        }
    }
}

