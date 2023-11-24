package com.example.divartask.data.repository.places

import com.example.divartask.data.database.PlacesDao
import com.example.divartask.data.database.PlacesEntity
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.repository.places.PlacesRepository
import com.example.divartask.presentation.util.convertToPlacesDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlacesRepositoryImp @Inject constructor(
    private val apiService: APIService,
    private val dao: PlacesDao
): PlacesRepository {
    override suspend fun getPlaces(): Flow<Resource<List<PlacesDomain>>> = flow{

        when(val result = apiService.getPlaces()){
            is NetworkResponse.APIError -> {
                emit(Resource.Error((result.apiErrorResponse as APIErrorResponse.NotFoundResponse).error.message?.errorMessage?:""))
            }
            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(result.exception.message.toString()))
            }
            is NetworkResponse.Success -> {
                dao.insertCities(convertToEntities(result))
            }
            else -> {
                emit(Resource.Error("error in network"))
            }
        }
        emit(Resource.Success(dao.getCities().convertToPlacesDomainModel()))
    }.flowOn(Dispatchers.IO)

    fun convertToEntities(result: NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>>): List<PlacesEntity>{
        val cityList: ArrayList<PlacesEntity> = arrayListOf()
        if (result is NetworkResponse.Success){
            result.body.cities?.forEach {
                cityList.add(
                    PlacesEntity(it.name?:"", it.id?:0)
                )
            }
        }
        return cityList
    }

}
