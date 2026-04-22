package com.ldb.mobileualachallenge.feature.cities.data.repository

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.ldb.mobileualachallenge.core.domain.model.CoreCoordinates
import com.ldb.mobileualachallenge.feature.cities.domain.model.City
import com.ldb.mobileualachallenge.feature.cities.domain.model.CityDetail

object FakeCityData {

    val cities = listOf(
        City(
            id = 4,
            countryCode = "US",
            name = "Alabama",
            coordinates = CoreCoordinates(
                latitude = 32.7504,
                longitude = -86.7503
            ),
            isFavorite = false
        ),
        City(
            id = 5,
            countryCode = "US",
            name = "Albuquerque",
            coordinates = CoreCoordinates(
                latitude = 35.0845,
                longitude = -106.6511
            ),
            isFavorite = false
        ),
        City(
            id = 6,
            countryCode = "US",
            name = "Anaheim",
            coordinates = CoreCoordinates(
                latitude = 33.8353,
                longitude = -117.9145
            ),
            isFavorite = false
        ),
        City(
            id = 7,
            countryCode = "US",
            name = "Arizona",
            coordinates = CoreCoordinates(
                latitude = 15.6333,
                longitude = -87.3167
            ),
            isFavorite = false
        ),
        City(
            id = 8,
            countryCode = "AU",
            name = "Sydney",
            coordinates = CoreCoordinates(
                latitude = -33.8679,
                longitude = 151.2073
            ),
            isFavorite = true
        )
    )

    val cityAdditionalInfo: CityDetail.AdditionalInfo = CityDetail.AdditionalInfo(
        shortDescription = LoremIpsum(15).values.joinToString(" "),
        longDescription = LoremIpsum(30).values.joinToString(" "),
        imageUrl = IMAGE_URL
    )

    private const val IMAGE_URL = "https://upload.wikimedia.org/" +
            "wikipedia/commons/thumb/1/1e/Puerto_Madero%2C_Buenos_Aires_%2840689219792%29_%28cropped%29.jpg" +
            "/330px-Puerto_Madero%2C_Buenos_Aires_%2840689219792%29_%28cropped%29.jpg"

}