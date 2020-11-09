package com.lupesoft.soccerleague.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.lupesoft.data.AppDataBase
import com.lupesoft.soccerleague.data.Status
import com.lupesoft.soccerleague.data.country.CountryRepository
import com.lupesoft.soccerleague.data.league.LeagueRepository
import com.lupesoft.soccerleague.data.team.TeamRepository
import com.lupesoft.soccerleague.utilities.getValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.*
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
class SoccerLeagueViewModelTest {
    private lateinit var appDataBase: AppDataBase
    private lateinit var viewModel: SoccerLeagueViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)

    @Inject
    lateinit var repository: TeamRepository

    @Inject
    lateinit var repositoryC: CountryRepository

    @Inject
    lateinit var repositoryL: LeagueRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        viewModel = SoccerLeagueViewModel(repository, repositoryC, repositoryL)
    }

    @After
    fun tearDown() {
        appDataBase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testLoadingServicecountrys() {
        Assert.assertEquals(getValue(viewModel.countrys).status, Status.LOADING)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testLoadingServiceleagues() {
        Assert.assertEquals(getValue(viewModel.leagues).status, Status.LOADING)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testLoadingServiceteams() {
        Assert.assertEquals(getValue(viewModel.teams("Spaint",s = "Sport",null)).status, Status.LOADING)
    }

}



