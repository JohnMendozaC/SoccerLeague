package com.lupesoft.soccerleague.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lupesoft.data.AppDataBase
import com.lupesoft.soccerleague.data.country.CountryDao
import com.lupesoft.soccerleague.data.country.CountryEntity
import com.lupesoft.soccerleague.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountryDaoTest {
    private lateinit var database: AppDataBase
    private lateinit var countryDao: CountryDao

    private val countryA = CountryEntity(1, "Andorra")
    private val countryB = CountryEntity(2, "Spain")
    private val countryC = CountryEntity(3, "England")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        countryDao = database.countryDao()
        countryDao.insertAll(listOf(countryA, countryB, countryC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetLeagues() {
        val countryList = getValue(countryDao.getCountryTest())
        Assert.assertThat(countryList.size, Matchers.equalTo(3))
        Assert.assertThat(countryList[0], Matchers.equalTo(countryA))
        Assert.assertThat(countryList[1], Matchers.equalTo(countryB))
        Assert.assertThat(countryList[2], Matchers.equalTo(countryC))
    }

    @Test
    fun testGetLeague() {
        Assert.assertThat(getValue(countryDao.getCountry(countryA.id)), Matchers.equalTo(countryA))
    }

}