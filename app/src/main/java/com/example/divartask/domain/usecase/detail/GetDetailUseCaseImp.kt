package com.example.divartask.domain.usecase.detail

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.domain.repository.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailUseCaseImp @Inject constructor(
    private val repository: DetailRepository
): GetDetailUseCase {
    override suspend fun invoke(token: String): Flow<Resource<List<DetailDomain>>> = repository.getDetail(token)
}