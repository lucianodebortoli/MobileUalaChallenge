package com.ldb.mobileualachallenge.feature.cities.data.remote.dto

import com.squareup.moshi.Json

data class WikiSummaryDto(
    @param:Json(name = "title") val title: String?,
    @param:Json(name = "description") val description: String?,
    @param:Json(name = "extract") val extract: String?,
    @param:Json(name = "thumbnail") val thumbnail: ImageSource?,
    @param:Json(name = "originalimage") val image: ImageSource?,
) {

    data class ImageSource(
        @param:Json(name = "source") val url: String
    )

}
