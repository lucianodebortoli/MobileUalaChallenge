package com.ldb.mobileualachallenge.core.presentation.component.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Composable
fun CoreOutlinedSurface(
    modifier: Modifier = Modifier,
    surfaceColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(Dimensions.CornerRadius.medium),
        border = BorderStroke(
            width = Dimensions.BorderSize.small,
            color = borderColor
        ),
        contentColor = contentColor,
        color = surfaceColor
    ) {
        content()
    }
}