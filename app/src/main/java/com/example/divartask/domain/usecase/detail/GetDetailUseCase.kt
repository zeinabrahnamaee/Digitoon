package com.example.divartask.domain.usecase.detail

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.domain.model.DetailDomain
import kotlinx.coroutines.flow.Flow

interface GetDetailUseCase {
    suspend operator fun invoke(token: String): Flow<Resource<List<DetailDomain>>>
}