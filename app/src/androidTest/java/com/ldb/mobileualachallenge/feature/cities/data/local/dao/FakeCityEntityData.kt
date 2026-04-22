package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityEntity

object FakeCityEntityData {

    // Cities:
    val alabama = CityEntity(id = 1, name = "Alabama", country = "US", latitude = 32.75, longitude = -86.75)
    val albuquerque = CityEntity(id = 2, name = "Albuquerque", country = "US", latitude = 35.08, longitude = -106.65)
    val anaheim = CityEntity(id = 3, name = "Anaheim", country = "US", latitude = 33.83, longitude = -117.91)
    val arizona = CityEntity(id = 4, name = "Arizona", country = "US", latitude = 15.63, longitude = -87.31)
    val sydney = CityEntity(id = 5, name = "Sydney", country = "AU", latitude = -33.86, longitude = 151.20)

    val allCities = listOf(alabama, albuquerque, anaheim, arizona, sydney)

}