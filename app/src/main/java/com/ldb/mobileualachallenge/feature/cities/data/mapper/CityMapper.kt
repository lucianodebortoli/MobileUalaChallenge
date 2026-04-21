package com.ldb.mobileualachallenge.feature.cities.data.mapper

import com.ldb.mobileualachallenge.core.domain.model.CoreCoordinates
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityEntity
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityFavoriteEmbeddedEntity
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.GistCityDto
import com.ldb.mobileualachallenge.feature.cities.domain.model.City

// Remote Mappers

/** Maps remote [GistCityDto] to local [CityEntity] */
fun GistCityDto.toLocalEntity() = CityEntity(
    id = id,
    name = name,
    country = countryPrefix,
    latitude = coordinates.latitude,
    longitude = coordinates.longitude
)

// Local Mappers

/** Maps local [CityFavoriteEmbeddedEntity] to domain [City] */
fun CityFavoriteEmbeddedEntity.toCity(): City = City(
    id = city.id,
    countryPrefix = city.country,
    name = city.name,
    coordinates = CoreCoordinates(
        latitude = city.latitude,
        longitude = city.longitude
    ),
    isFavorite = isFavorite
)