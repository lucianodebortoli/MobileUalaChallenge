package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.ldb.mobileualachallenge.R
import com.ldb.mobileualachallenge.core.presentation.component.preview.CorePreview
import com.ldb.mobileualachallenge.core.presentation.extension.toFlagEmoji
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions

@Stable
data class CityDescriptionData(
    val summary: String? = null,
    val description: String? = null,
    val coordinates: String? = null,
    val countryCode: String? = null,
)

@Composable
fun CityDescriptionSection(
    modifier: Modifier = Modifier,
    data: CityDescriptionData
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimensions.Spacing.medium),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.large)
    ) {
        CityInfo(
            countryCode = data.countryCode,
            coordinates = data.coordinates
        )
        HorizontalDivider()
        data.summary?.let { text ->
            SummaryText(text)
        }
        data.description?.let { text ->
            DescriptionText(text)
        }
    }
}

@Composable
private fun CityInfo(
    countryCode: String?,
    coordinates: String?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.small)
    ) {
        countryCode?.let {
            Text(
                text = "$it ${it.toFlagEmoji()}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        coordinates?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SummaryText(text: String) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.small)) {
        Text(
            text = stringResource(R.string.feature_cities_details_summary),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun DescriptionText(text: String) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimensions.Spacing.small)) {
        Text(
            text = stringResource(R.string.feature_cities_details_description),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

internal fun previewCityDescriptionData() = CityDescriptionData(
    summary = LoremIpsum(20).values.joinToString(" "),
    description = LoremIpsum(50).values.joinToString(" "),
    countryCode = "AR",
    coordinates = "-34.6132°, -74.0060°"
)

@Preview
@Composable
private fun Preview() {
    CorePreview(0.dp) {
        CityDescriptionSection(
            data = previewCityDescriptionData()
        )
    }
}