package com.ldb.mobileualachallenge.di

import android.content.Context
import androidx.room.Room
import com.ldb.mobileualachallenge.data.local.dao.CityDao
import com.ldb.mobileualachallenge.data.local.database.AppDatabase
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
    fun provideCityDao(database: AppDatabase): CityDao = database.cityDao()

}