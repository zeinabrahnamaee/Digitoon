package com.example.divartask.data.repository.places

import com.example.divartask.data.base.APIService
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.domain.repository.places.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlacesRepositoryImp @Inject constructor(
    private val apiService: APIService
): PlacesRepository {
    override suspend fun getPlaces(): Flow<Resource<PlacesListData>> = flow {
        emit(Resource.Success(apiService.getPlaces()))
    }

}
