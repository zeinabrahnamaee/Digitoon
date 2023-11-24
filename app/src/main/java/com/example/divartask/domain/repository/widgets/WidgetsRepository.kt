package com.example.divartask.domain.repository.widgets

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam

interface WidgetsRepository {
    suspend fun getWidgets(id: Int, postsParam: WidgetsParam): NetworkResponse<Widgets, APIErrorResponse<ErrorBody>>
}