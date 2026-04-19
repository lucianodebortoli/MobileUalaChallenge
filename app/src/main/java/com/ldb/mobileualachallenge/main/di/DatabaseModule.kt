package com.ldb.mobileualachallenge.main.di

import android.content.Context
import androidx.room.Room
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.FavoriteCityDao
import com.ldb.mobileualachallenge.feature.cities.data.local.dao.CityFavoriteJoinedDao
import com.ldb.mobileualachallenge.main.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "cities_db"
        ).build()
    }

    @Provides
    fun provideCityDao(database: AppDatabase): CityDao {
        return database.cityDao()
    }

    @Provides
    fun provideFavoriteCityDao(database: AppDatabase): FavoriteCityDao {
        return database.favoriteCityDao()
    }

    @Provides
    fun provideCityWithFavoritesDao(database: AppDatabase): CityFavoriteJoinedDao {
        return database.cityWithFavoritesDao()
    }

}