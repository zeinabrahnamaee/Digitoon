package com.example.divartask.data.base

import com.example.divartask.data.entity.PlacesListData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface APIService {
    @GET("/api/v1/place/list")
    suspend fun getPlaces(
    ):PlacesListData
}