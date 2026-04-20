package com.ldb.mobileualachallenge.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.theme.ChallengeTheme
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClicked: (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        navigationIcon = {
            onBackClicked?.let {
                IconButton(onClick = it) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back Button",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun FieldPreview() {
    ChallengeTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(Dimensions.Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)
        ) {
            CoreTopBar(
                title = "Screen title",
                onBackClicked = {}
            )
        }
    }
}