package com.example.divartask.domain.usecase.detail

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.DetailData
import com.example.divartask.domain.repository.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailUseCaseImp @Inject constructor(
    private val repository: DetailRepository
): GetDetailUseCase {
    override fun invoke(token: String): Flow<Resource<DetailData>> = flow {
        val result = repository.getDetail(token)
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