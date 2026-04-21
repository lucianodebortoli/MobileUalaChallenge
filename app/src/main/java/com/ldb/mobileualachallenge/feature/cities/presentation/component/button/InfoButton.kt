package com.ldb.mobileualachallenge.feature.cities.presentation.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun InfoButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClicked: () -> Unit
) {
    Button(
        modifier = modifier.minimumInteractiveComponentSize(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = when (isSelected) {
                true -> MaterialTheme.colorScheme.primaryContainer
                false -> MaterialTheme.colorScheme.onPrimaryContainer
            }
        ),
        onClick = onClicked
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Info Button",
        )
    }
}