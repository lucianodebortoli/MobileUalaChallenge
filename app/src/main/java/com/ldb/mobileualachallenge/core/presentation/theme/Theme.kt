package com.ldb.mobileualachallenge.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = OceanicLight,
    secondary = GrayLight,
    tertiary = GoldLight
)

private val LightColorScheme = lightColorScheme(
    primary = OceanicDark,
    secondary = GrayDark,
    tertiary = GoldDark,
    background = WhiteLight,
    surface = WhiteLight,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = BlackLight,
    onSurface = BlackLight,
)

@Composable
fun ChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}