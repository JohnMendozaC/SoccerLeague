package com.lupesoft.soccerleague.data.league

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LeagueDao {

    @Query("SELECT * FROM leagues ORDER BY strLeague")
    fun getLeagues(): Flow<List<LeagueEntity>>

    @Query("SELECT * FROM leagues")
    fun getLaguesTest(): LiveData<List<LeagueEntity>>

    @Query("SELECT * FROM leagues WHERE id = :id")
    fun getLeagues(id: String): LiveData<LeagueEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<LeagueEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg elem: LeagueEntity): List<Long>

}