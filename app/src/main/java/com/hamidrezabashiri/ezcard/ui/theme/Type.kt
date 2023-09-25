package com.hamidrezabashiri.ezcard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hamidrezabashiri.ezcard.R

val fontIran = FontFamily(
    Font(R.font.iran_sansx_regular, weight = FontWeight.Normal),
    Font(R.font.iran_sansx_bold, weight = FontWeight.Bold),
    Font(R.font.iran_sansx_black, weight = FontWeight.Black),
    Font(R.font.iran_sansx_light, weight = FontWeight.Light),
    Font(R.font.iran_sansx_medium, weight = FontWeight.Medium),
    Font(R.font.iran_sansx_thin, weight = FontWeight.Thin),
    Font(R.font.iran_sansx_ultralight, weight = FontWeight.ExtraLight),
    Font(R.font.iran_sansx_demi_bold, weight = FontWeight.SemiBold),


    )

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        letterSpacing = 1.25.sp,
        lineHeight = 48.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = 0.75.sp,
        lineHeight = 40.sp
    ),
    displaySmall = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 32.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        letterSpacing = 0.75.sp,
        lineHeight = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 32.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.25.sp,
        lineHeight = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = 0.25.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        letterSpacing = 0.15.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.25.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp,
        lineHeight = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = 0.4.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = fontIran,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.4.sp,
        lineHeight = 16.sp
    )
)













