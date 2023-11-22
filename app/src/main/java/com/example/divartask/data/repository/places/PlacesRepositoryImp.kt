package com.example.divartask.data.repository.places

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.base.APIService
import com.example.divartask.data.network.ErrorBody
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.domain.repository.places.PlacesRepository
import javax.inject.Inject

class PlacesRepositoryImp @Inject constructor(
    private val apiService: APIService
): PlacesRepository {
    override suspend fun getPlaces(): NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>> {
        return apiService.getPlaces()
    }

}
