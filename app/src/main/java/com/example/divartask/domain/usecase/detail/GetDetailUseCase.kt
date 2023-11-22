package com.example.divartask.domain.usecase.detail

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.DetailData
import com.example.divartask.data.entity.PostsData
import kotlinx.coroutines.flow.Flow

interface GetDetailUseCase {
    operator fun invoke(token: String): Flow<Resource<DetailData>>
}