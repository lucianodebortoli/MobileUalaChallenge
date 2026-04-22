package com.ldb.mobileualachallenge.feature.cities.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.CityFavoriteEmbeddedEntity
import com.ldb.mobileualachallenge.feature.cities.data.local.entity.FavoriteCityEntity
import com.ldb.mobileualachallenge.main.data.database.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for testing the search algorithm given the query and filter inputs.
 * Since the [CityFavoriteJoinedDao] uses [CityDao] and [FavoriteCityDao],
 * This Test also tests the other Daos indirectly.
 */
@RunWith(AndroidJUnit4::class)
class CityFavoriteJoinedDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var cityDao: CityDao
    private lateinit var favoriteCityDao: FavoriteCityDao
    private lateinit var joinedDao: CityFavoriteJoinedDao

    @Before
    fun setUp() {
        runBlocking {
            // Use Room builder for in memory database:
            database = Room.inMemoryDatabaseBuilder(
                context = ApplicationProvider.getApplicationContext(),
                klass = AppDatabase::class.java
            ).allowMainThreadQueries().build()
            // Initialize Daos:
            cityDao = database.cityDao()
            favoriteCityDao = database.favoriteCityDao()
            joinedDao = database.cityWithFavoritesDao()
            // Insert all test cities:
            cityDao.insertCities(FakeCityEntityData.allCities)
            // Add one favorite:
            favoriteCityDao.addFavorite(FavoriteCityEntity(id = FakeCityEntityData.sydney.id))
        }
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun emptyQuery_noFilter_getsCitiesSortedByNameCountry() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "",
                filterFavorites = false
            )
            val expected = FakeCityEntityData.allCities.sortedWith(
                compareBy({ it.name }, { it.country })
            )
            assertThat(result.map { it.city }).containsExactlyElementsIn(expected).inOrder()
        }
    }

    @Test
    fun singleLetterQuery_getsCitiesStartingWithLetter() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "A",
                filterFavorites = false
            )
            assertThat(result.map { it.city }).containsExactly(
                FakeCityEntityData.alabama,
                FakeCityEntityData.albuquerque,
                FakeCityEntityData.anaheim,
                FakeCityEntityData.arizona
            ).inOrder()
        }
    }

    @Test
    fun multiLetterQuery_narrowsToListWithPrefix() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "Al",
                filterFavorites = false
            )
            assertThat(result.map { it.city }).containsExactly(
                FakeCityEntityData.alabama,
                FakeCityEntityData.albuquerque
            ).inOrder()
        }
    }

    @Test
    fun caseInsensitiveQuery_matchesCasedNames() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "ALA",
                filterFavorites = false
            )
            assertThat(result.map { it.city }).containsExactly(FakeCityEntityData.alabama)
        }
    }

    @Test
    fun queryNoMatches_returnsEmptyList() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "Zzz",
                filterFavorites = false
            )
            assertThat(result).isEmpty()
        }
    }

    @Test
    fun noQueryWithFilter_returnsOnlyFavorites() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "",
                filterFavorites = true
            )
            assertThat(result.map { it.city }).containsExactly(FakeCityEntityData.sydney).inOrder()
            assertThat(result.all { it.isFavorite }).isTrue()
        }
    }

    @Test
    fun queryWithFilter_returnsFavoritesMatchingPrefix() {
        runBlocking {
            val result = joinedDao.getFilteredCitiesAsList(
                searchQuery = "S",
                filterFavorites = true
            )
            assertThat(result.map { it.city }).containsExactly(FakeCityEntityData.sydney)
            assertThat(result.single().isFavorite).isTrue()
        }
    }

    /**
     * Helper function: Loads all rows from the DAO's PagingSource in one page.
     * Useful for getting non paged results during testing.
     */
    private suspend fun CityFavoriteJoinedDao.getFilteredCitiesAsList(
        searchQuery: String,
        filterFavorites: Boolean
    ): List<CityFavoriteEmbeddedEntity> {
        val filteredCities = getFilteredCities(
            searchQuery = searchQuery,
            filterFavorites = filterFavorites
        )
        val loadResult = filteredCities.load(
            params = PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 100,
                placeholdersEnabled = false
            )
        )
        check(loadResult is PagingSource.LoadResult.Page) {
            "Expected a Page, got $loadResult"
        }
        return loadResult.data
    }
}
