package com.example.divartask.domain.repository.detail

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.DetailData
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getDetail(token: String): Flow<Resource<DetailData>>
}