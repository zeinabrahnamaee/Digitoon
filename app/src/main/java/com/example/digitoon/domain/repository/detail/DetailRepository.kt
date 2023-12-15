package com.example.digitoon.domain.repository.detail

import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.DetailData

interface DetailRepository {
    suspend fun getDetail(token: String): Resource<DetailData>
}