package com.kool.customer.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kool.customer.R

// Almarai — the typeface used by the Figma file (Regular / Bold / ExtraBold).
val AlmaraiFamily = FontFamily(
    Font(R.font.almarai_regular, FontWeight.Normal),
    Font(R.font.almarai_bold, FontWeight.Bold),
    Font(R.font.almarai_extrabold, FontWeight.ExtraBold),
)

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
        fontFamily = AlmaraiFamily,
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
