package com.lupesoft.soccerleague.data.country

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryVo(
    val id: Int? = 0,
    val name: String
) : Parcelable