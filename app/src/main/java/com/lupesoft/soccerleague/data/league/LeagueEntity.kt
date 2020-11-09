package com.lupesoft.soccerleague.data.league

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class LeagueEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate: String?
)

fun List<LeagueEntity>.toDomainModel(): List<LeagueVo> {
    return map { LeagueVo(it.id, it.strLeague, it.strSport, it.strLeagueAlternate) }
}