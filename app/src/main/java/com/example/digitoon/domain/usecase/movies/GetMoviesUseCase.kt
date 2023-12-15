package com.example.digitoon.domain.usecase.movies

import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.MoviesData
import kotlinx.coroutines.flow.Flow

interface GetMoviesUseCase {
    suspend operator fun invoke():  Flow<Resource<MoviesData>>
}