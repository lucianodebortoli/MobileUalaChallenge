package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Stable
data class CityDescriptionData(
    val summary: String? = null,
    val description: String? = null
)

@Composable
fun CityDescriptionSection(
    modifier: Modifier = Modifier,
    data: CityDescriptionData
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.medium)
    ) {
        data.summary?.let {
            Text(
                text = stringResource(R.string.feature_cities_details_summary),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        data.description?.let {
            Text(
                text = stringResource(R.string.feature_cities_details_description),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    CorePreview {
        CityDescriptionSection(
            data = CityDescriptionData(
                summary = LoremIpsum(15).values.joinToString(" "),
                description = LoremIpsum(50).values.joinToString(" ")
            )
        )
    }
}