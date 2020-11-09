package com.lupesoft.soccerleague.api.dto

import com.google.gson.annotations.SerializedName
import com.lupesoft.soccerleague.data.team.League
import com.lupesoft.soccerleague.data.team.Team

data class Teams(
    @field:SerializedName("teams") val teams: List<TeamDto>?,
)

data class TeamDto(
    @field:SerializedName("idTeam") val idTeam: String?,
    @field:SerializedName("idAPIfootball") val idAPIfootball: String?,
    @field:SerializedName("strTeam") val strTeam: String?,
    @field:SerializedName("strTeamShort") val strTeamShort: String?,
    @field:SerializedName("strAlternate") val strAlternate: String?,
    @field:SerializedName("intFormedYear") val intFormedYear: String?,
    @field:SerializedName("strSport") val strSport: String?,
    @field:SerializedName("strLeague") val strLeague: String?,
    @field:SerializedName("idLeague") val idLeague: String?,
    @field:SerializedName("strLeague2") val strLeague2: String?,
    @field:SerializedName("idLeague2") val idLeague2: String?,
    @field:SerializedName("strLeague3") val strLeague3: String?,
    @field:SerializedName("idLeague3") val idLeague3: String?,
    @field:SerializedName("strLeague4") val strLeague4: String?,
    @field:SerializedName("idLeague4") val idLeague4: String?,
    @field:SerializedName("strLeague5") val strLeague5: String?,
    @field:SerializedName("idLeague5") val idLeague5: String?,
    @field:SerializedName("strLeague6") val strLeague6: String?,
    @field:SerializedName("idLeague6") val idLeague6: String?,
    @field:SerializedName("strLeague7") val strLeague7: String?,
    @field:SerializedName("idLeague7") val idLeague7: String?,
    @field:SerializedName("strManager") val strManager: String?,
    @field:SerializedName("strStadium") val strStadium: String?,
    @field:SerializedName("strKeywords") val strKeywords: String?,
    @field:SerializedName("strRSS") val strRSS: String?,
    @field:SerializedName("strStadiumThumb") val strStadiumThumb: String?,
    @field:SerializedName("strStadiumDescription") val strStadiumDescription: String?,
    @field:SerializedName("strStadiumLocation") val strStadiumLocation: String?,
    @field:SerializedName("intStadiumCapacity") val intStadiumCapacity: String?,
    @field:SerializedName("strWebsite") val strWebsite: String?,
    @field:SerializedName("strFacebook") val strFacebook: String?,
    @field:SerializedName("strTwitter") val strTwitter: String?,
    @field:SerializedName("strInstagram") val strInstagram: String?,
    @field:SerializedName("strDescriptionEN") val strDescriptionEN: String?,
    @field:SerializedName("strGender") val strGender: String?,
    @field:SerializedName("strCountry") val strCountry: String?,
    @field:SerializedName("strTeamBadge") val strTeamBadge: String?,
    @field:SerializedName("strTeamJersey") val strTeamJersey: String?,
    @field:SerializedName("strYoutube") val strYoutube: String?,
    @field:SerializedName("strLocked") val strLocked: String?
)

fun List<TeamDto>.toDomainModel(): List<Team> {
    return map {
        Team(
            idTeam = it.idTeam,
            name = it.strTeam,
            stadium = it.strStadium,
            badge = it.strTeamBadge,
            description = it.strDescriptionEN,
            foundationYear = it.intFormedYear,
            jersey = it.strTeamJersey,
            events = listOf(
                League(idLeague = it.idLeague, name = it.strLeague),
                League(idLeague = it.idLeague2, name = it.strLeague2),
                League(idLeague = it.idLeague3, name = it.strLeague3),
                League(idLeague = it.idLeague4, name = it.strLeague4),
                League(idLeague = it.idLeague5, name = it.strLeague5),
                League(idLeague = it.idLeague6, name = it.strLeague6),
                League(idLeague = it.idLeague7, name = it.strLeague7),
            ),
            webSite = it.strWebsite,
            webFacebook = it.strFacebook,
            webTwiter = it.strTwitter,
            webInstagram = it.strInstagram,
            video = it.strYoutube
        )
    }
}