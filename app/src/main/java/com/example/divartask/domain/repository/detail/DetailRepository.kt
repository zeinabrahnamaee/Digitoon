package com.example.divartask.domain.repository.detail

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.network.ErrorBody
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.entity.DetailData

interface DetailRepository {
    suspend fun getDetail(token: String): NetworkResponse<DetailData, APIErrorResponse<ErrorBody>>
}