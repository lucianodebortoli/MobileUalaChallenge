package com.ldb.mobileualachallenge.core.presentation.component.image

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.ldb.mobileualachallenge.core.domain.model.ImageUrl
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview

@Composable
fun CoreAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: ImageUrl?
) {
    val isLocalInspection = LocalInspectionMode.current
    val showPlaceHolder = remember { mutableStateOf(true) }
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onState = { state ->
            val asyncFailed: Boolean = state !is AsyncImagePainter.State.Success
            showPlaceHolder.value = asyncFailed || isLocalInspection || imageUrl.isNullOrBlank()
        },
    )
    Crossfade(
        modifier = modifier,
        targetState = !showPlaceHolder.value
    ) { isLoaded ->
        if (isLoaded) {
            ContentImage(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
            )
        } else {
            PlaceHolderImage(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ContentImage(
    modifier: Modifier,
    painter: AsyncImagePainter,
) {
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = "Image Content",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun PlaceHolderImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        imageVector = Icons.Default.ImageNotSupported,
        contentDescription = "Image Placeholder",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface),
        contentScale = ContentScale.FillHeight
    )
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CoreAsyncImage(
            modifier = Modifier.fillMaxSize(),
            imageUrl = null
        )
    }
}