package com.hamidrezabashiri.ezcard.utils

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hamidrezabashiri.ezcard.ui.theme.TurquoiseDark
import com.hamidrezabashiri.ezcard.ui.theme.pink200

@Composable
@Preview
fun CardItem() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                shape = RoundedCornerShape(32.dp),
                brush = Brush.linearGradient(0.0f to pink200, 1.0f to TurquoiseDark)
            )
            .padding(16.dp)
    ) {
        Column() {
            Text(
                text = "6105 4444 1059 1045",
                fontSize = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "تاریخ انقضا", color = Color.White, fontSize = 12.sp)
                    Text(
                        text = "1406/05",
                        color = Color.White,
                        fontFamily = FontFamily(Typeface.DEFAULT_BOLD)
                    )
                }

            }
        }
    }

}