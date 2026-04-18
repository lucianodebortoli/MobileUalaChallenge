package com.ldb.mobileualachallenge.feature.cities.di

import com.ldb.mobileualachallenge.feature.cities.data.repository.CityRepositoryImpl
import com.ldb.mobileualachallenge.feature.cities.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CitiesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(
        repository: CityRepositoryImpl
    ): CityRepository

}