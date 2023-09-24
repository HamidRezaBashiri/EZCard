package com.hamidrezabashiri.ezcard.ui.common

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.ui.theme.TurquoiseDark
import com.hamidrezabashiri.ezcard.ui.theme.pink200

@Composable
fun CardItem(
    card: CreditCard,
    onDeleteClicked: () -> Unit,
    onCopyToClipBoard: (String) -> Unit,
    modifier: Modifier = Modifier,
) {


    Column(
        modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(
                shape = RoundedCornerShape(32.dp),
                brush = Brush.linearGradient(0.0f to pink200, 1.0f to TurquoiseDark)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 24.dp, top = 16.dp)
                .height(48.dp),
            verticalAlignment = Alignment.Top
        ) {

            IconButton(onClick = { onDeleteClicked.invoke() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.trash),
                    contentDescription = "delete btn", tint = Color.Unspecified
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
            )
            Icon(
                imageVector = ImageVector.vectorResource(card.bankLogoResId),
                contentDescription = "bank logo", tint = Color.Unspecified
            )

        }

        Row(
            modifier = Modifier.padding(top = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (card.cardNumber.isNotEmpty()) {
                Text(text = card.cardNumber, color = Color.White)
                IconButton(onClick = { onCopyToClipBoard.invoke(card.cardNumber) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.copy),
                        contentDescription = "copy button",
                        tint = Color.Unspecified
                    )
                }

            }
        }
        Row(
            modifier = Modifier.padding(top = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            if (card.iban.isNotEmpty() && card.iban != "IR") {
                Text(text = card.iban, color = Color.White)
                IconButton(onClick = { onCopyToClipBoard.invoke(card.iban) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.copy),
                        contentDescription = "copy button",
                        tint = Color.Unspecified
                    )
                }

            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (card.cardHolderName.isNotEmpty()) {
                Text(
                    text = card.cardHolderName,
                    color = Color.White,
                    modifier = Modifier.padding(start = 32.dp)
                )
                IconButton(onClick = { onCopyToClipBoard.invoke(card.cardHolderName) }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.copy),
                        contentDescription = "copy button",
                        tint = Color.Unspecified
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            if (card.expirationDate.isNotEmpty() && card.expirationDate != "/") {

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
            }
            Spacer(modifier = Modifier.weight(1f))

            Row(verticalAlignment = Alignment.Bottom) {

                if (card.cvv2.isNotEmpty()) {
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


