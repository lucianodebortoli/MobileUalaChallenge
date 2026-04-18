package com.ldb.mobileualachallenge.feature.cities.data.remote.api

import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.WikiSearchDto
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.WikiSummaryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WikiApi {

    @GET("w/rest.php/v1/search/page")
    suspend fun searchPage(
        @Query("q") query: String,
        @Query("limit") limit: Int = 1
    ): Response<WikiSearchDto>

    @GET("api/rest_v1/page/summary/{title}")
    suspend fun getSummary(
        @Path("title") title: String
    ): Response<WikiSummaryDto>

}