package com.ldb.mobileualachallenge.core.presentation.component.empty

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview

@Composable
fun CoreEmptyView(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CoreEmptyView(
            modifier = Modifier.fillMaxSize(),
            title = "Empty screen"
        )
    }
}