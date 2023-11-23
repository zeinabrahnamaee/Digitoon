package com.example.divartask.domain.repository.detail

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.DetailData

interface DetailRepository {
    suspend fun getDetail(token: String): NetworkResponse<DetailData, APIErrorResponse<ErrorBody>>
}