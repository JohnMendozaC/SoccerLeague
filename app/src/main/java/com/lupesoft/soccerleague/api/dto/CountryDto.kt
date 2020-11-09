package com.lupesoft.soccerleague.api.dto

import com.google.gson.annotations.SerializedName
import com.lupesoft.soccerleague.data.country.CountryEntity
import com.lupesoft.soccerleague.data.country.CountryVo

data class Countrys(
    @field:SerializedName("countries") val countrys: List<CountryDto>
)

data class CountryDto(
    @field:SerializedName("name_en") val name: String
)

fun List<CountryDto>.toDomainModel(): List<CountryVo> {
    return map { CountryVo(name = it.name) }
}

fun List<CountryDto>.toDatabaseModel(): List<CountryEntity> {
    return map { CountryEntity(name = it.name) }
}
