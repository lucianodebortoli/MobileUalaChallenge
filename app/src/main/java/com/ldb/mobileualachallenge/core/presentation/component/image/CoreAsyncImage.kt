package com.ldb.mobileualachallenge.core.presentation.component.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.ldb.mobileualachallenge.core.domain.model.ImageUrl
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview

@Composable
fun CoreAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: ImageUrl?
) {
    val isLocalInspection = LocalInspectionMode.current
    if (isLocalInspection || imageUrl.isNullOrBlank()) {
        PlaceHolderImage(modifier = modifier)
        return
    }
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = "Image Content",
        contentScale = ContentScale.Crop,
        loading = { PlaceHolderImage(modifier = Modifier.fillMaxSize()) },
        error = { PlaceHolderImage(modifier = Modifier.fillMaxSize()) }
    )
}

@Composable
private fun PlaceHolderImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainerLow),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Default.ImageNotSupported,
            contentDescription = "Image Placeholder",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun Preview() {
    CorePreview(padding = 0.dp) {
        CoreAsyncImage(
            modifier = Modifier.fillMaxSize(),
            imageUrl = null
        )
    }
}