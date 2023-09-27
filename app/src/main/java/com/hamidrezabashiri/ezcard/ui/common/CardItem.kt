package com.hamidrezabashiri.ezcard.ui.common

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.LayoutDirection
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
    val context = LocalContext.current

    val bankIconsMap = mapOf(
        "ansar" to R.drawable.ansar,
        "ayandeh" to R.drawable.ayandeh,
        "bank_markazi" to R.drawable.bank_markazi,
        "blu_bank" to R.drawable.blu_bank,
        "caspian" to R.drawable.caspian,
        "unknown" to R.drawable.chip,
        "dey" to R.drawable.dey,
        "eghtesad_novin" to R.drawable.eghtesad_novin,
        "futurebank" to R.drawable.futurebank,
        "gardeshgari" to R.drawable.gardeshgari,
        "ghavamin" to R.drawable.ghavamin,
        "hekmat" to R.drawable.hekmat,
        "iran_europe" to R.drawable.iran_europe,
        "iran_venezuela" to R.drawable.iran_venezuela,
        "iran_zamin" to R.drawable.iran_zamin,
        "karafarin" to R.drawable.karafarin,
        "keshavarzi" to R.drawable.keshavarzi,
        "khavar_mianeh" to R.drawable.khavar_mianeh,
        "kosar" to R.drawable.kosar,
        "maskan" to R.drawable.maskan,
        "mehr_eghtesad" to R.drawable.mehr_eghtesad,
        "mehr_iran" to R.drawable.mehr_iran,
        "melall" to R.drawable.melall,
        "mellat" to R.drawable.mellat,
        "melli" to R.drawable.melli,
        "noor" to R.drawable.noor,
        "parsian" to R.drawable.parsian,
        "pasargad" to R.drawable.pasargad,
        "post" to R.drawable.post,
        "refah" to R.drawable.refah,
        "resalat" to R.drawable.resalat,
        "saderat" to R.drawable.saderat,
        "saman" to R.drawable.saman,
        "sanat_madan" to R.drawable.sanat_madan,
        "sarmayeh" to R.drawable.sarmayeh,
        "sepah" to R.drawable.sepah,
        "shahr" to R.drawable.shahr,
        "sina" to R.drawable.sina,
        "standard_chartered" to R.drawable.standard_chartered,
        "taavon_eslami" to R.drawable.taavon_eslami,
        "tejarat" to R.drawable.tejarat,
        "tosee" to R.drawable.tosee,
        "tosee_saderat" to R.drawable.tosee_saderat,
        "tosee_taavon" to R.drawable.tosee_taavon
    )

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {


        Column(
            modifier
                .defaultMinSize(minHeight = 250.dp)
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .background(
                    shape = RoundedCornerShape(32.dp),
                    brush = Brush.linearGradient(0.0f to pink200, 1.0f to TurquoiseDark)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 24.dp, top = 8.dp)
                        .height(36.dp),
                    verticalAlignment = Alignment.Top
                ) {

                    IconButton(onClick = { onDeleteClicked.invoke() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.trash),
                            contentDescription = "delete btn",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(32.dp)
                                .align(Alignment.CenterVertically)

                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                    )
                    val bankIconResId = bankIconsMap[card.bankName] ?: R.drawable.chip

                    Icon(
                        imageVector = ImageVector.vectorResource(bankIconResId),
                        contentDescription = "bank logo",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically)
                    )

                }

            }


            Row(
                modifier = Modifier.padding(top = 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (card.cardNumber.isNotEmpty()) {
                    Text(text = formatCardNumber(card.cardNumber), color = Color.White)
                    IconButton(onClick = {
                        showToast(context)
                        onCopyToClipBoard.invoke(card.cardNumber)
                    }) {
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {

                if (card.cardHolderName.isNotEmpty()) {

                    Row(
                        Modifier
                            .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = card.cardHolderName,
                            color = Color.White,
//                        modifier = Modifier.padding(start = 32.dp)
                        )
                        IconButton(onClick = {
                            showToast(context)
                            onCopyToClipBoard.invoke(card.cardHolderName)
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.copy),
                                contentDescription = "copy button",
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
//                if (card.cvv2.isNotEmpty()) {
//
//                    Row(verticalAlignment = Alignment.Bottom) {
//                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                            Text(text = "CVV2", color = Color.White, fontSize = 12.sp)
//                            Text(
//                                text = card.cvv2,
//                                color = Color.White,
//                                fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
//                            )
//                        }
//
//                        IconButton(onClick = {
//                            showToast(context)
//                            onCopyToClipBoard.invoke(card.cvv2)
//                        }) {
//                            Icon(
//                                imageVector = ImageVector.vectorResource(R.drawable.copy),
//                                contentDescription = "copy button",
//                                tint = Color.Unspecified
//                            )
//                        }
//                    }
//                }
                Spacer(modifier = Modifier.weight(1f))

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
                        IconButton(onClick = {
                            showToast(context)
                            onCopyToClipBoard.invoke(card.expirationDate)
                        }) {
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
}

private fun formatCardNumber(cardNumber: String): String {
    val formattedNumber = StringBuilder()
    for (i in cardNumber.indices) {
        if (i > 0 && i % 4 == 0) {
            formattedNumber.append(" ") // Add a space every 4 digits
        }
        formattedNumber.append(cardNumber[i])
    }
    return formattedNumber.toString()
}

fun showToast(context: Context) {
    Toast.makeText(context, "در کلیپ برد کپی شد!", Toast.LENGTH_SHORT).show()
}

