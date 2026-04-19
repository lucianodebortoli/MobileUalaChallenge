package com.ldb.mobileualachallenge.feature.cities.domain.model

import com.ldb.mobileualachallenge.core.domain.model.ImageUrl

data class CityDetail(
    val city: City,
    val additionalInfo: AdditionalInfo
) {

    data class AdditionalInfo(
        val shortDescription: String?,
        val longDescription: String?,
        val imageUrl: ImageUrl?
    )

}
