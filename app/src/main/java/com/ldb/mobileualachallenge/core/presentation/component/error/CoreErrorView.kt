package com.ldb.mobileualachallenge.core.presentation.component.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.button.CoreButton
import com.ldb.mobileualachallenge.core.presentation.component.button.CoreButtonDefaults
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions


@Composable
fun CoreErrorView(
    modifier: Modifier = Modifier,
    title: String,
    onRetryClicked: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(Dimensions.IconSize.large),
                imageVector = Icons.Default.Error,
                tint = MaterialTheme.colorScheme.error,
                contentDescription = "Error Icon"
            )
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error
            )
            CoreButton(
                title = stringResource(R.string.feature_cities_action_retry),
                colors = CoreButtonDefaults.colors().copy(
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    containerColor = MaterialTheme.colorScheme.errorContainer
                ),
                onClick = onRetryClicked
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CoreErrorView(
            modifier = Modifier.fillMaxSize(),
            title = "Ups.. we have an error!",
            onRetryClicked = {}
        )
    }
}