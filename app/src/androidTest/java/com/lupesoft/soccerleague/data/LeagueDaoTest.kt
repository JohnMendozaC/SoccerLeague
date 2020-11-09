package com.lupesoft.soccerleague.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lupesoft.data.AppDataBase
import com.lupesoft.soccerleague.data.league.LeagueDao
import com.lupesoft.soccerleague.data.league.LeagueEntity
import com.lupesoft.soccerleague.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LeagueDaoTest {
    private lateinit var database: AppDataBase
    private lateinit var leagueDao: LeagueDao

    private val leagueA = LeagueEntity("4328", "English Premier League", "Soccer", "Premier League")
    private val leagueB =
        LeagueEntity("4329", "English League Championship", "Soccer", "Championship")
    private val leagueC =
        LeagueEntity("4330", "Scottish Premier League", "Soccer", "Scottish Premiership")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        leagueDao = database.leagueDao()
        leagueDao.insertAll(listOf(leagueA, leagueB, leagueC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetLeagues() {
        val leagueList = getValue(leagueDao.getLaguesTest())
        Assert.assertThat(leagueList.size, Matchers.equalTo(3))
        Assert.assertThat(leagueList[0], Matchers.equalTo(leagueA))
        Assert.assertThat(leagueList[1], Matchers.equalTo(leagueB))
        Assert.assertThat(leagueList[2], Matchers.equalTo(leagueC))
    }

    @Test
    fun testGetLeague() {
        Assert.assertThat(getValue(leagueDao.getLeagues(leagueA.id)), Matchers.equalTo(leagueA))
    }

}