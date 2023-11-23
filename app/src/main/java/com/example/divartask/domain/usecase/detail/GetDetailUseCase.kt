package com.example.divartask.domain.usecase.detail

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.DetailData
import kotlinx.coroutines.flow.Flow

interface GetDetailUseCase {
    operator fun invoke(token: String): Flow<Resource<DetailData>>
}