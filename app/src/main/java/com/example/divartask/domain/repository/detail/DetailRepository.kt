package com.example.divartask.domain.repository.detail

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.domain.model.DetailDomain
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getDetail(token: String): Flow<Resource<List<DetailDomain>>>
}