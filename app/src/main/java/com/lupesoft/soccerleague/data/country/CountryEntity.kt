package com.lupesoft.soccerleague.data.country

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countrys")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String
)

fun List<CountryEntity>.toDomainModel(): List<CountryVo> {
    return map { CountryVo(id = it.id, name = it.name) }
}