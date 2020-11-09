package com.lupesoft.soccerleague.api

import com.lupesoft.soccerleague.api.dto.Countrys
import com.lupesoft.soccerleague.api.dto.Leagues
import com.lupesoft.soccerleague.api.dto.Teams
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("all_countries.php")
    suspend fun getCountrys(): Response<Countrys>


    @GET("all_leagues.php")
    suspend fun getLeagues(): Response<Leagues>

    @GET("search_all_teams.php")
    suspend fun getTeamsByCountry(
        @Query("s") s: String? = null,
        @Query("c") c: String? = null,
        @Query("l") l: String? = null
    ): Response<Teams>

    companion object {
        private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"
        const val SPAIN = "Spain"
        const val SPORT = "Soccer"

        fun create(): Api {
            val logger = HttpLoggingInterceptor().apply { level = Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api::class.java)
        }

    }

}