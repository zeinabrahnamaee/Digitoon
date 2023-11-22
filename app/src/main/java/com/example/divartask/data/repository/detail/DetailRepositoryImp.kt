package com.example.divartask.data.repository.detail

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.base.APIService
import com.example.divartask.data.network.ErrorBody
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.entity.DetailData
import com.example.divartask.domain.repository.detail.DetailRepository
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val apiService: APIService
): DetailRepository {
    override suspend fun getDetail(token: String): NetworkResponse<DetailData, APIErrorResponse<ErrorBody>> {
        return apiService.getDetail(token)
    }

}
