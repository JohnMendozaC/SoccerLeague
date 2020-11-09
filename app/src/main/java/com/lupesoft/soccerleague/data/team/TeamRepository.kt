package com.lupesoft.soccerleague.data.team

import com.lupesoft.soccerleague.api.Api
import com.lupesoft.soccerleague.api.dto.toDomainModel
import com.lupesoft.soccerleague.api.response.ApiEmptyResponse
import com.lupesoft.soccerleague.api.response.ApiErrorResponse
import com.lupesoft.soccerleague.api.response.ApiResponse
import com.lupesoft.soccerleague.api.response.ApiSuccessResponse
import com.lupesoft.soccerleague.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TeamRepository @Inject constructor(private val service: Api) {
    fun getFromNetwork(country: String?, s: String?, league: String?): Flow<Resource<List<Team>>> {
        val flow = flow {
            val response = service.getTeamsByCountry(c = country, s = s, l = league)
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    emit(Resource.success(apiResponse.body.teams?.toDomainModel()))
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
}