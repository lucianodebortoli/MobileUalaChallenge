package com.ldb.mobileualachallenge.feature.cities.data.remote.dto

import com.squareup.moshi.Json

data class WikiSearchDto(
    @param:Json(name = "pages") val pages: List<WikiPage>
) {

    data class WikiPage(
        @param:Json(name = "title") val title: String,
        @param:Json(name = "description") val description: String?,
        @param:Json(name = "excerpt") val excerptHtml: String?,
        @param:Json(name = "thumbnail") val thumbnail: Thumbnail?
    ) {

        data class Thumbnail(
            @param:Json(name = "url") val url: String
        )

    }

}
