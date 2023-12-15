package com.example.digitoon.domain.repository.movies

import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.MoviesData

interface MoviesRepository {
    suspend fun getMovies(): Resource<MoviesData>
}