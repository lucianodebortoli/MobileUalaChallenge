package com.ldb.mobileualachallenge.di

import com.ldb.mobileualachallenge.data.repository.CityRepositoryImpl
import com.ldb.mobileualachallenge.domain.repository.CityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(
        repository: CityRepositoryImpl
    ): CityRepository

}