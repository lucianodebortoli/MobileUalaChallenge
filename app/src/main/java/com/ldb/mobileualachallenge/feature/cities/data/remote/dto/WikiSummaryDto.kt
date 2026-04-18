package com.ldb.mobileualachallenge.feature.cities.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WikiSummaryDto(
    @SerialName("title") val title: String?,
    @SerialName("description") val description: String?,
    @SerialName("extract") val extract: String?,
    @SerialName("thumbnail") val thumbnail: ImageSource?,
    @SerialName("originalimage") val image: ImageSource?,
) {

    @Serializable
    data class ImageSource(
        @SerialName("source") val source: String
    )

}