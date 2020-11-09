package com.lupesoft.soccerleague.data.country

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lupesoft.soccerleague.data.country.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM countrys ORDER BY name")
    fun getCountrys(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM countrys")
    fun getCountryTest(): LiveData<List<CountryEntity>>

    @Query("SELECT * FROM countrys WHERE id = :id")
    fun getCountry(id: Int): LiveData<CountryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<CountryEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg elem: CountryEntity): List<Long>

}