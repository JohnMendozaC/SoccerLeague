package com.lupesoft.soccerleague.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lupesoft.soccerleague.data.Resource
import com.lupesoft.soccerleague.data.country.CountryRepository
import com.lupesoft.soccerleague.data.country.CountryVo
import com.lupesoft.soccerleague.data.league.LeagueRepository
import com.lupesoft.soccerleague.data.league.LeagueVo
import com.lupesoft.soccerleague.data.team.Team
import com.lupesoft.soccerleague.data.team.TeamRepository

class SoccerLeagueViewModel @ViewModelInject constructor(
    private val repository: TeamRepository,
    private val repositoryC: CountryRepository,
    private val repositoryL: LeagueRepository
) : ViewModel() {

    fun teams(
        country: String? = null,
        s: String?,
        league: String? = null
    ): LiveData<Resource<List<Team>>> =
        repository.getFromNetwork(country, s, league).asLiveData()

    val countrys: LiveData<Resource<List<CountryVo>>>
        get() = repositoryC.getFromDatabase().asLiveData()

    val leagues: LiveData<Resource<List<LeagueVo>>>
        get() = repositoryL.getFromDatabase().asLiveData()
}