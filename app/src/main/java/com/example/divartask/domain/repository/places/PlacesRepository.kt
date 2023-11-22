package com.example.divartask.domain.repository.places

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.network.ErrorBody
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.entity.PlacesListData

interface PlacesRepository {
    suspend fun getPlaces(): NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>>
}