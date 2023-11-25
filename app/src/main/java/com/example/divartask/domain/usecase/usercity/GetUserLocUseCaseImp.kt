package com.example.divartask.domain.usecase.usercity

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.UserLocationData
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.repository.widgets.WidgetsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLocUseCaseImp @Inject constructor(
    private val repository: WidgetsRepository
): GetUserLocUseCase {
    override suspend fun invoke(lat: Double?, long: Double?): Flow<Resource<PlacesDomain>> = repository.getUserLocation(lat, long)
}