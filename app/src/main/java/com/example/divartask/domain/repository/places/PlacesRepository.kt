package com.example.divartask.domain.repository.places

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.domain.model.PlacesDomain
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun getPlaces(): Flow<Resource<List<PlacesDomain>>>
}