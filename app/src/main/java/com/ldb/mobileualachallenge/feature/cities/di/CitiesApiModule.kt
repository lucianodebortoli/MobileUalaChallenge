package com.ldb.mobileualachallenge.feature.cities.di

import com.ldb.mobileualachallenge.core.data.network.GistRetrofit
import com.ldb.mobileualachallenge.core.data.network.WikiRetrofit
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.GistApi
import com.ldb.mobileualachallenge.feature.cities.data.remote.api.WikiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CitiesApiModule {

    @Provides
    fun provideGistApi(
        @GistRetrofit retrofit: Retrofit
    ): GistApi {
        return retrofit.create(GistApi::class.java)
    }

    @Provides
    fun provideWikiApi(
        @WikiRetrofit retrofit: Retrofit
    ): WikiApi {
        return retrofit.create(WikiApi::class.java)
    }

}