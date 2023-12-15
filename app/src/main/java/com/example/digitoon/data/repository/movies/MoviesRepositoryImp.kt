package com.example.digitoon.data.repository.movies

import com.example.digitoon.data.remote.APIService
import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.MoviesData
import com.example.digitoon.data.remote.network.APIErrorResponse
import com.example.digitoon.data.remote.network.NetworkResponse
import com.example.digitoon.domain.repository.movies.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    private val apiService: APIService,
) : MoviesRepository {
    override suspend fun getMovies(): Resource<MoviesData> {

        return when (val result = apiService.getMovies()) {
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
