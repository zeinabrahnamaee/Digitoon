package com.example.divartask.domain.repository.places

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PlacesListData
import kotlinx.coroutines.flow.Flow

interface PlacesRepository {
    suspend fun getPlaces(): Flow<Resource<PlacesListData>>
}