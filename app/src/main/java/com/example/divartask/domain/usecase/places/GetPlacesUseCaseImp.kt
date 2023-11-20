package com.example.divartask.domain.usecase.places

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.domain.repository.places.PlacesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPlacesUseCaseImp @Inject constructor(
    private val repository: PlacesRepository
): GetPlacesUseCase {
    override fun invoke(): Flow<Resource<PlacesListData>> = flow {
        repository.getPlaces().collect{
            emit(it)
        }
    }
}