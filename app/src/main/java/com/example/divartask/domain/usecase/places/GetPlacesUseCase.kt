package com.example.divartask.domain.usecase.places

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.domain.model.PlacesDomain
import kotlinx.coroutines.flow.Flow

interface GetPlacesUseCase {
    suspend operator fun invoke():  Flow<Resource<List<PlacesDomain>>>
}