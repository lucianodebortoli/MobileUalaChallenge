package com.ldb.mobileualachallenge.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.theme.ChallengeTheme
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreTextField(
    modifier: Modifier = Modifier,
    value: String,
    fieldPlaceholder: String? = null,
    iconLeading: ImageVector? = null,
    iconTrailing: ImageVector? = null,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    colors: TextFieldColors = CoreTextFieldDefaults.colors(),
    onValueChange: (String) -> Unit,
    padding: PaddingValues = PaddingValues(Dimensions.Spacing.medium),
    shape: Shape = RoundedCornerShape(Dimensions.RoundCorners.medium),
    textStyle: TextStyle = TextStyle.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val interactionSource = remember { MutableInteractionSource() }
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        enabled = isEnabled,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = isEnabled,
                singleLine = singleLine,
                colors = colors,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                isError = isError,
                placeholder = fieldPlaceholder?.let { text ->
                    {
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                },
                leadingIcon = iconLeading?.let { icon ->
                    {
                        Icon(
                            modifier = Modifier.size(Dimensions.IconSize.small),
                            imageVector = icon,
                            contentDescription = "Field Leading Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                trailingIcon = iconTrailing?.let { icon ->
                    {
                        Icon(
                            modifier = Modifier.size(Dimensions.IconSize.small),
                            imageVector = icon,
                            contentDescription = "Field Trailing Icon",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                contentPadding = padding,
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = isEnabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = colors,
                        shape = shape,
                    )
                }
            )
        }
    )
}

private object CoreTextFieldDefaults {

    @Composable
    fun colors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        errorTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        errorContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
        errorBorderColor = MaterialTheme.colorScheme.error,
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
            CoreTextField(
                modifier = Modifier.fillMaxWidth(),
                fieldPlaceholder = "Placeholder",
                value = "",
                onValueChange = {}
            )
            CoreTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "Text input field",
                onValueChange = {}
            )
        }
    }
}