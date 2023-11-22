package com.example.divartask.domain.usecase.detail

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.DetailData
import com.example.divartask.data.entity.PostsData
import com.example.divartask.domain.repository.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetDetailUseCaseImp @Inject constructor(
    private val repository: DetailRepository
): GetDetailUseCase {
    override fun invoke(token: String): Flow<Resource<DetailData>> = flow {
        repository.getDetail(token).collect{
            emit(it)
        }
    }
}