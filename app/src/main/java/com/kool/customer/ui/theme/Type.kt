package com.kool.customer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Almarai is downloaded at runtime via downloadable fonts or bundled.
// Fallback to system default (sans-serif) for now; replace Font() calls with
// actual bundled .ttf resources once assets are available.
val AlmaraiFamily = FontFamily.Default
val InterFamily = FontFamily.Default

val KoolTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 33.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 21.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = InterFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = AlmaraiFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
    ),
)
