package com.ldb.mobileualachallenge.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = OceanMedium,
    primaryContainer = OceanDark,
    onPrimaryContainer = OceanLight,
    secondary = GreenMedium,
    secondaryContainer = GreenDark,
    onSecondaryContainer = GreenLight,
    tertiary = GoldMedium,
    tertiaryContainer = GoldDark,
    onTertiaryContainer = GoldLight,
    background = BlackSoft,
    surface = BlackSoft,
    onPrimary = BlackSoft,
    onSecondary = BlackSoft,
    onTertiary = BlackSoft,
    onBackground = WhiteSoft,
    onSurface = WhiteSoft,
)

private val LightColorScheme = lightColorScheme(
    primary = OceanMedium,
    primaryContainer = OceanLight,
    onPrimaryContainer = OceanDark,
    secondary = GreenMedium,
    secondaryContainer = GreenLight,
    onSecondaryContainer = GreenDark,
    tertiary = GoldMedium,
    tertiaryContainer = GoldLight,
    onTertiaryContainer = GoldDark,
    background = WhiteSoft,
    surface = WhiteSoft,
    onPrimary = WhiteSoft,
    onSecondary = WhiteSoft,
    onTertiary = WhiteSoft,
    onBackground = BlackSoft,
    onSurface = BlackSoft,
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