package com.example.divartask.domain.usecase.places

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.repository.places.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlacesUseCaseImp @Inject constructor(
    private val repository: PlacesRepository
): GetPlacesUseCase {
    override suspend fun invoke(): Flow<Resource<List<PlacesDomain>>> = repository.getPlaces()

}