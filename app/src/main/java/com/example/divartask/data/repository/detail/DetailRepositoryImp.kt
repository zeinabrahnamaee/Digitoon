package com.example.divartask.data.repository.detail

import com.example.divartask.data.base.APIService
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.DetailData
import com.example.divartask.domain.repository.detail.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val apiService: APIService
): DetailRepository {
    override suspend fun getDetail(token: String): Flow<Resource<DetailData>> = flow{
        emit(Resource.Success(apiService.getDetail(token)))
    }

}
