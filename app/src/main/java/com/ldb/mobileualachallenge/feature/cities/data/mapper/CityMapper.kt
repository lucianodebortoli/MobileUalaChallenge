package com.ldb.mobileualachallenge.feature.cities.data.mapper

import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityEntity
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityWithFavoriteEntity
import com.ldb.mobileualachallenge.feature.cities.data.remote.dto.CityRemoteDto
import com.ldb.mobileualachallenge.feature.cities.domain.model.City

// Remote Mappers

/** Maps remote [CityRemoteDto] to local [CityEntity] */
fun CityRemoteDto.toLocal(): CityEntity = CityEntity(
    id = id,
    name = name,
    country = countryPrefix,
    latitude = coordinates.latitude,
    longitude = coordinates.longitude
)

// Local Mappers

/** Maps local [CityWithFavoriteEntity] to domain [City] */
fun CityWithFavoriteEntity.toDomain(): City = City(
    id = city.id,
    countryPrefix = city.country,
    name = city.name,
    coordinates = City.Coordinates(
        latitude = city.latitude,
        longitude = city.longitude
    ),
    isFavorite = isFavorite
)