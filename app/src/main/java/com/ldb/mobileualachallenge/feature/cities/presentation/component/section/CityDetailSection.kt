package com.ldb.mobileualachallenge.feature.cities.presentation.component.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ldb.mobileualachallenge.core.presentation.theme.ChallengeTheme
import com.ldb.mobileualachallenge.core.presentation.theme.Dimensions


@Composable
fun CityDetailSection(
    modifier: Modifier = Modifier
) {

}

@Preview
@Composable
private fun Preview() {
    ChallengeTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(Dimensions.Spacing.medium)
        ) {
            CityDetailSection(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}