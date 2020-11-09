package com.lupesoft.soccerleague.data.team

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    val idTeam: String?,
    val name: String?,
    val stadium: String?,
    val badge: String?,
    val description: String?,
    val foundationYear: String?,
    val jersey: String?,
    val events: List<League>?,
    val webSite: String?,
    val webFacebook: String?,
    val webTwiter: String?,
    val webInstagram: String?,
    val video: String?
) : Parcelable

@Parcelize
data class League(
    val idLeague: String? = null,
    val name: String?
) : Parcelable
