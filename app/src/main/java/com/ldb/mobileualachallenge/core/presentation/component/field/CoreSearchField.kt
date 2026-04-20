package com.ldb.mobileualachallenge.core.presentation.component.field

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Composable
fun CoreSearchField(
    modifier: Modifier = Modifier,
    value: String,
    isEnabled: Boolean = true,
    padding: PaddingValues = PaddingValues(Dimensions.Spacing.medium),
    trailingIcon: ImageVector? = null,
    iconColors: CoreFieldIconColors = CoreFieldDefaults.iconColors(),
    onValueChange: (String) -> Unit,
    onTrailingIconClicked: () -> Unit,
    onKeyboardSearchClicked: () -> Unit = {},
) {
    CoreField(
        modifier = modifier,
        value = value,
        fieldPlaceholder = stringResource(R.string.core_text_field_search),
        iconLeading = Icons.Default.Search,
        iconTrailing = trailingIcon,
        iconColors = iconColors,
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
                onKeyboardSearchClicked()
            }
        ),
        onTrailingIconClicked = onTrailingIconClicked
    )
}

@Preview
@Composable
private fun SearchFieldPreview() {
    CorePreview {
        CoreSearchField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            trailingIcon = Icons.Default.FilterAlt,
            onValueChange = {},
            onTrailingIconClicked = {}
        )
    }
}