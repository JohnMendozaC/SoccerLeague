package com.lupesoft.soccerleague.data.league

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueVo(
    val id: String,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate: String?
) : Parcelable