package com.ldb.mobileualachallenge.core.presentation.component.field

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoreField(
    modifier: Modifier = Modifier,
    value: String,
    fieldPlaceholder: String? = null,
    iconLeading: ImageVector? = null,
    iconTrailing: ImageVector? = null,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    fieldColors: TextFieldColors = CoreFieldDefaults.fieldColors(),
    iconColors: CoreFieldIconColors = CoreFieldDefaults.iconColors(),
    padding: PaddingValues = PaddingValues(Dimensions.Spacing.medium),
    shape: Shape = RoundedCornerShape(Dimensions.CornerRadius.medium),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit,
    onLeadingIconClicked: () -> Unit = {},
    onTrailingIconClicked: () -> Unit = {}
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
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = isEnabled,
                singleLine = singleLine,
                colors = fieldColors,
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
                        IconButton(
                            modifier = Modifier.size(Dimensions.IconSize.small),
                            onClick = onLeadingIconClicked
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "Field Leading Icon",
                                tint = iconColors.leadingIconColor
                            )
                        }
                    }
                },
                trailingIcon = iconTrailing?.let { icon ->
                    {
                        IconButton(
                            modifier = Modifier.size(Dimensions.IconSize.small),
                            onClick = onTrailingIconClicked
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "Field Trailing Icon",
                                tint = iconColors.trailingIconColor
                            )
                        }
                    }
                },
                contentPadding = padding,
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = isEnabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = fieldColors,
                        shape = shape,
                    )
                }
            )
        }
    )
}

data class CoreFieldIconColors(
    val leadingIconColor: Color,
    val trailingIconColor: Color
)

object CoreFieldDefaults {

    @Composable
    fun fieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        errorTextColor = MaterialTheme.colorScheme.error,
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        errorContainerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
        errorBorderColor = MaterialTheme.colorScheme.error,
    )

    @Composable
    fun iconColors(): CoreFieldIconColors = CoreFieldIconColors(
        leadingIconColor = MaterialTheme.colorScheme.primary,
        trailingIconColor = MaterialTheme.colorScheme.primary,
    )

}

@Preview
@Composable
private fun FieldPreview() {
    CorePreview {
        Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)) {
            CoreField(
                modifier = Modifier.fillMaxWidth(),
                fieldPlaceholder = "Placeholder",
                value = "",
                onValueChange = {}
            )
            CoreField(
                modifier = Modifier.fillMaxWidth(),
                value = "Text input field",
                onValueChange = {}
            )
        }
    }
}