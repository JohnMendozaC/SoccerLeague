package com.lupesoft.soccerleague.api.dto

import com.google.gson.annotations.SerializedName
import com.lupesoft.soccerleague.data.league.LeagueEntity
import com.lupesoft.soccerleague.data.league.LeagueVo

data class Leagues(
    @field:SerializedName("leagues") val leagues: List<LeagueDto>
)

data class LeagueDto(
    @field:SerializedName("idLeague") val idLeague: String,
    @field:SerializedName("strLeague") val strLeague: String?,
    @field:SerializedName("strSport") val strSport: String?,
    @field:SerializedName("strLeagueAlternate") val strLeagueAlternate: String?
)

fun List<LeagueDto>.toDatabaseModel(): List<LeagueEntity> {
    return map { LeagueEntity(it.idLeague, it.strLeague, it.strSport, it.strLeagueAlternate) }
}

fun List<LeagueDto>.toDomainModel(): List<LeagueVo> {
    return map { LeagueVo(it.idLeague, it.strLeague, it.strSport, it.strLeagueAlternate) }
}