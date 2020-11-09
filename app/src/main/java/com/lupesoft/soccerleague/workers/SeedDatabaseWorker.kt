package com.lupesoft.soccerleague.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.lupesoft.data.AppDataBase
import com.lupesoft.soccerleague.api.dto.Countrys
import com.lupesoft.soccerleague.api.dto.Leagues
import com.lupesoft.soccerleague.api.dto.toDatabaseModel
import com.lupesoft.soccerleague.utilities.COUNTRY_DATA_FILENAME
import com.lupesoft.soccerleague.utilities.LEAGUE_DATA_FILENAME
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(COUNTRY_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val countrys: Countrys = Gson().fromJson(jsonReader, Countrys::class.java)
                    val database = AppDataBase.getInstance(applicationContext)
                    database.countryDao().insertAll(countrys.countrys.toDatabaseModel())
                    Result.success()
                }
            }
            applicationContext.assets.open(LEAGUE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val leagues: Leagues = Gson().fromJson(jsonReader, Leagues::class.java)
                    val database = AppDataBase.getInstance(applicationContext)
                    database.leagueDao().insertAll(leagues.leagues.toDatabaseModel())
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}