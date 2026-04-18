package com.ldb.mobileualachallenge.feature.cities.domain.repository

interface CityRepository {

    /**
     * Loads all cities.
     * Intended to be used for loading all cities when initializing the app.
     * TODO signature
     */
    //suspend fun loadCities()

    /**
     * Gets next page of cities.
     * Intended to be used for getting a batch of paginated cities.
     * TODO signature
     */
    //suspend fun getCitiesNextPage()

    /**
     * Gets a city detail.
     * Intended to be used when more information about a city is required.
     * TODO signature
     */
    //suspend fun getCityDetail()

    /**
     * Marks a city as favorite.
     * This can be undone by using [removeFavoriteCity]
     * TODO signature
     */
    //suspend fun saveFavoriteCity()

    /**
     * Unmarks a city as favorite.
     * Intended to be used when a city is already marked as favorite using [saveFavoriteCity].
     * TODO signature
     */
    //suspend fun removeFavoriteCity()

}