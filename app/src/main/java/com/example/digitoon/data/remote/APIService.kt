package com.example.digitoon.data.remote

import com.example.digitoon.data.remote.entity.DetailData
import com.example.digitoon.data.remote.entity.MoviesData
import com.example.digitoon.data.remote.network.APIErrorResponse
import com.example.digitoon.data.remote.network.ErrorBody
import com.example.digitoon.data.remote.network.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/?apikey=3e974fca")
    suspend fun getDetail(@Query("i") imdbId: String): NetworkResponse<DetailData, APIErrorResponse<ErrorBody>>

    @GET("/?apikey=3e974fca&s=batman")
    suspend fun getMovies(): NetworkResponse<MoviesData, APIErrorResponse<ErrorBody>>
}