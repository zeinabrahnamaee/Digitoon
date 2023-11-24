package com.example.divartask.domain.usecase.widgets

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.repository.widgets.WidgetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWidgetsUseCaseImp @Inject constructor(
    private val repository: WidgetsRepository
): GetWidgetsUseCase {
    override fun invoke(id: Int, postsParam: WidgetsParam): Flow<Resource<Widgets>> = flow {
        val result = repository.getWidgets(id, postsParam)
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