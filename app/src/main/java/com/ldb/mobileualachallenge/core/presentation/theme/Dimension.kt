package com.ldb.mobileualachallenge.core.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class SpacingDimensions(
    val small: Dp,
    val medium: Dp,
    val large: Dp
)

data class RoundCornersDimensions(
    val small: Dp,
    val medium: Dp,
    val large: Dp
)

data class IconSizeDimensions(
    val small: Dp,
    val medium: Dp,
    val large: Dp
)

object Dimensions {

    val Spacing = SpacingDimensions(
        small = 8.dp,
        medium = 12.dp,
        large = 16.dp
    )

    val RoundCorners = RoundCornersDimensions(
        small = 10.dp,
        medium = 15.dp,
        large = 20.dp
    )

    val IconSize = IconSizeDimensions(
        small = 24.dp,
        medium = 32.dp,
        large = 48.dp
    )

}
