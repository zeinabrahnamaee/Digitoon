package com.example.divartask.domain.usecase.usercity

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.UserLocationData
import com.example.divartask.domain.model.PlacesDomain
import kotlinx.coroutines.flow.Flow

interface GetUserLocUseCase {
    suspend operator fun invoke(lat: Double?, long: Double?): Flow<Resource<PlacesDomain>>
}