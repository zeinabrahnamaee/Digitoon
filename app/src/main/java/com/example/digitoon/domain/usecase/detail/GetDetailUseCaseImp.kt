package com.example.digitoon.domain.usecase.detail

import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.DetailData
import com.example.digitoon.domain.repository.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailUseCaseImp @Inject constructor(
    private val repository: DetailRepository
): GetDetailUseCase {
    override suspend fun invoke(token: String): Flow<Resource<DetailData>> = flow {
        emit(repository.getDetail(token))
    }
}