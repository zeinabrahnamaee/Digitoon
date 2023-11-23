package com.example.divartask.data.repository.places

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.domain.repository.places.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImp @Inject constructor(
    private val apiService: APIService
): PlacesRepository {
    override suspend fun getPlaces(): NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>> {
        return apiService.getPlaces()
    }

}
