package com.example.digitoon.data.repository.detail

import com.example.digitoon.data.remote.APIService
import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.DetailData
import com.example.digitoon.data.remote.network.APIErrorResponse
import com.example.digitoon.data.remote.network.NetworkResponse
import com.example.digitoon.domain.repository.detail.DetailRepository
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val apiService: APIService,
): DetailRepository {
    override suspend fun getDetail(token: String): Resource<DetailData> {
        return when (val result = apiService.getDetail(token)) {
            is NetworkResponse.APIError -> {
                Resource.Error(
                    (result.apiErrorResponse as APIErrorResponse.NotFoundResponse).error.errorMessage
                        ?: ""
                )
            }

            is NetworkResponse.NetworkError -> {
                Resource.Error(result.exception.message.toString())
            }

            is NetworkResponse.Success -> {
                Resource.Success(result.body)
            }

            else -> {
                Resource.Error("error in network")
            }
        }
    }
}
