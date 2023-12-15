package com.example.digitoon.domain.usecase.movies

import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.MoviesData
import com.example.digitoon.domain.repository.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCaseImp @Inject constructor(
    private val repository: MoviesRepository
): GetMoviesUseCase {
    override suspend fun invoke(): Flow<Resource<MoviesData>> = flow{
        emit(repository.getMovies())
    }

}