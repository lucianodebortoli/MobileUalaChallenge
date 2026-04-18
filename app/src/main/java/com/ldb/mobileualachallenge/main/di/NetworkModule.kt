package com.ldb.mobileualachallenge.main.di

import com.ldb.mobileualachallenge.core.data.network.GistRetrofit
import com.ldb.mobileualachallenge.core.data.network.WikiRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().apply {
            add(KotlinJsonAdapterFactory())
        }.build()
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()
    }

    @GistRetrofit
    @Provides
    @Singleton
    fun provideGithubGistRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl("https://gist.githubusercontent.com/")
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create(moshi))
        }.build()
    }

    @WikiRetrofit
    @Provides
    @Singleton
    fun provideWikipediaRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl("https://en.wikipedia.org/")
            client(okHttpClient)
            addConverterFactory(MoshiConverterFactory.create(moshi))
        }.build()
    }

}