package com.ldb.mobileualachallenge.feature.cities.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WikiSearchDto(
    @SerialName("pages") val pages: List<WikiPage>
) {

    @Serializable
    data class WikiPage(
        @SerialName("title") val title: String,
        @SerialName("description") val description: String?,
        @SerialName("excerpt") val excerptHtml: String?,
        @SerialName("thumbnail") val thumbnail: Thumbnail?
    ) {

        @Serializable
        data class Thumbnail(
            @SerialName("url") val url: String
        )

    }

}