package com.ldb.mobileualachallenge.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.theme.ChallengeTheme
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Composable
fun CoreSearchField(
    modifier: Modifier = Modifier,
    value: String,
    isEnabled: Boolean = true,
    padding: PaddingValues = PaddingValues(Dimensions.Spacing.medium),
    onValueChange: (String) -> Unit,
    onSearchClicked: () -> Unit,
) {
    CoreTextField(
        modifier = modifier,
        value = value,
        fieldPlaceholder = stringResource(R.string.core_text_field_search),
        iconLeading = Icons.Default.Search,
        isEnabled = isEnabled,
        isError = false,
        singleLine = true,
        onValueChange = onValueChange,
        padding = padding,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked()
            }
        ),
    )
}

@Preview
@Composable
private fun SearchFieldPreview() {
    ChallengeTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(Dimensions.Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)
        ) {
            CoreSearchField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                onSearchClicked = {}
            )
        }
    }
}