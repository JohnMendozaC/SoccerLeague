package com.lupesoft.soccerleague.data.league

import com.lupesoft.soccerleague.api.Api
import com.lupesoft.soccerleague.api.dto.toDatabaseModel
import com.lupesoft.soccerleague.api.dto.toDomainModel
import com.lupesoft.soccerleague.api.response.ApiEmptyResponse
import com.lupesoft.soccerleague.api.response.ApiErrorResponse
import com.lupesoft.soccerleague.api.response.ApiResponse
import com.lupesoft.soccerleague.api.response.ApiSuccessResponse
import com.lupesoft.soccerleague.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeagueRepository @Inject constructor(
    private val leagueDao: LeagueDao,
    private val service: Api
) {


    private fun getFromNetwork(): Flow<Resource<List<LeagueVo>>> {
        val flow = flow {
            val response = service.getLeagues()
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    emit(Resource.loading(null, "Saving data in database..."))
                    leagueDao.insertAll(
                        *apiResponse.body.leagues.toDatabaseModel().toTypedArray()
                    )
                    emit(Resource.success(apiResponse.body.leagues.toDomainModel()))
                }
                is ApiEmptyResponse -> {
                    emit(Resource.error(null, 0, "Empty response"))
                }
                is ApiErrorResponse -> {
                    emit(Resource.error(null, apiResponse.code, apiResponse.message))
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Service fetching...")) }
            .catch { exception ->
                with(exception) {
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }

    fun getFromDatabase(): Flow<Resource<List<LeagueVo>>> {
        val flow = flow {
            leagueDao.getLeagues().collect {
                if (it.isNotEmpty()) {
                    emit(Resource.success(it.toDomainModel()))
                } else {
                    getFromNetwork().collect { service ->
                        emit(service)
                    }
                }
            }
        }

        return flow
            .onStart { emit(Resource.loading(null, "Loading from database...")) }
            .catch { exception ->
                with(exception) {
                    emit(Resource.error(null, 0, message))
                }
            }
            .flowOn(Dispatchers.IO)
    }
}