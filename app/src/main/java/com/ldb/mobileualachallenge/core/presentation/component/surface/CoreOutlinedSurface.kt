package com.ldb.mobileualachallenge.core.presentation.component.surface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
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

@Preview
@Composable
private fun Preview() {
    CorePreview {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)
        ) {
            CoreOutlinedSurface {
                Text(
                    modifier = Modifier.padding(Dimensions.Spacing.medium),
                    text = "Default content"
                )
            }
            CoreOutlinedSurface(
                surfaceColor = MaterialTheme.colorScheme.primaryContainer,
                borderColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Text(
                    modifier = Modifier.padding(Dimensions.Spacing.medium),
                    text = "Light content"
                )
            }
            CoreOutlinedSurface(
                surfaceColor = MaterialTheme.colorScheme.onPrimaryContainer,
                borderColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    modifier = Modifier.padding(Dimensions.Spacing.medium),
                    text = "Dark content"
                )
            }
        }
    }
}