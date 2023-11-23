package com.example.divartask.domain.repository.places

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.PlacesListData

interface PlacesRepository {
    suspend fun getPlaces(): NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>>
}