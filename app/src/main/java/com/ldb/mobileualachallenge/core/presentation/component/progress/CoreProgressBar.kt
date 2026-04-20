package com.ldb.mobileualachallenge.core.presentation.component.progress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Composable
fun CoreProgressBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    progressColor: Color = MaterialTheme.colorScheme.onPrimary,
    trackColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = progressColor,
                trackColor = trackColor,
                strokeCap = StrokeCap.Round
            )
            title?.let { text ->
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProgressPreview() {
    CorePreview {
        CoreProgressBar(
            modifier = Modifier.fillMaxSize(),
            title = "Loading..."
        )
    }
}