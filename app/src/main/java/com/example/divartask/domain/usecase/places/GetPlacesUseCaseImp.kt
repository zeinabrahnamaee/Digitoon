package com.example.divartask.domain.usecase.places

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.domain.repository.places.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlacesUseCaseImp @Inject constructor(
    private val repository: PlacesRepository
): GetPlacesUseCase {
    override fun invoke(): Flow<Resource<PlacesListData>> = flow {
        val result = repository.getPlaces()
        when(result){
            is NetworkResponse.APIError -> {
                emit(Resource.Error((result.apiErrorResponse as APIErrorResponse.NotFoundResponse).error.message?.errorMessage?:""))
            }
            is NetworkResponse.Empty -> {}
            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(result.exception.toString()))
            }
            is NetworkResponse.ProtocolError -> {
                emit(Resource.Error(result.exception.toString()))
            }
            is NetworkResponse.Success -> {
                emit(Resource.Success(result.body))
            }
            is NetworkResponse.UnknownError -> {

            }
        }
    }
}