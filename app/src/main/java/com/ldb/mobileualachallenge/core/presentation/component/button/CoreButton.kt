package com.ldb.mobileualachallenge.core.presentation.component.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview

@Composable
fun CoreButton(
    title: String,
    colors: ButtonColors = CoreButtonDefaults.colors(),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = colors
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = colors.contentColor
        )
    }
}

object CoreButtonDefaults {

    @Composable
    fun colors(): ButtonColors = ButtonDefaults.buttonColors().copy(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    )

}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CoreButton(
            title = "Click me",
            onClick = {}
        )
    }
}