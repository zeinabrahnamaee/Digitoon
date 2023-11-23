package com.example.divartask.domain.usecase.places

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.PlacesListData
import kotlinx.coroutines.flow.Flow

interface GetPlacesUseCase {
    operator fun invoke(): Flow<Resource<PlacesListData>>
}