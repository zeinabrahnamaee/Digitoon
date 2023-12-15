package com.example.digitoon.domain.usecase.detail

import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.DetailData
import kotlinx.coroutines.flow.Flow

interface GetDetailUseCase {
    suspend operator fun invoke(token: String): Flow<Resource<DetailData>>
}