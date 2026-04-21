package com.ldb.mobileualachallenge.feature.cities.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit

@Singleton
class CitySyncPreferences @Inject constructor(
    @ApplicationContext context: Context
) {

    private val citiesPreferences: SharedPreferences = context.getSharedPreferences(
        CITIES_PREFERENCES_FILE_NAME,
        Context.MODE_PRIVATE
    )

    fun getLastCitiesSyncTimestampMs(): Long? {
        return citiesPreferences.getLong(CITIES_LAST_SYNC_KEY, 0L).takeIf { it > 0L }
    }

    fun setLastCitiesSyncTimestampMs(timestampMs: Long) {
        citiesPreferences.edit { putLong(CITIES_LAST_SYNC_KEY, timestampMs) }
    }

    fun isSyncRecent(nowMs: Long = System.currentTimeMillis()): Boolean {
        val lastMs = getLastCitiesSyncTimestampMs() ?: return false
        return (nowMs - lastMs) < CITIES_LAST_SYNC_THRESHOLD_MS
    }

    private companion object {
        const val CITIES_PREFERENCES_FILE_NAME = "cities_sync_preferences"
        const val CITIES_LAST_SYNC_KEY = "cities_last_sync_timestamp"
        const val CITIES_LAST_SYNC_THRESHOLD_MS = 60L * 60L * 1000L // 1 hour
    }

}
