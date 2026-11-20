package com.kool.customer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val KoolColorScheme = lightColorScheme(
    primary = KoolPink,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    primaryContainer = KoolPinkLight,
    secondary = KoolPinkDark,
    background = KoolBackground,
    surface = KoolSurface,
    onBackground = KoolTextPrimary,
    onSurface = KoolTextPrimary,
)

@Composable
fun KoolTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = KoolColorScheme,
        typography = KoolTypography,
        content = content,
    )
}
