package com.ldb.mobileualachallenge.core.presentation.component.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ldb.mobileualachallenge.core.presentation.theme.ChallengeTheme
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Composable
fun CorePreview(
    content: @Composable () -> Unit
) {
    ChallengeTheme {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(Dimensions.Spacing.medium)
        ) {
            content()
        }
    }
}
