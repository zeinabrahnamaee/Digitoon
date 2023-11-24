package com.example.divartask.data.repository.widgets

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.repository.widgets.WidgetsRepository
import javax.inject.Inject

class WidgetsRepositoryImp @Inject constructor(
    private val apiService: APIService
): WidgetsRepository {
    override suspend fun getWidgets(id: Int, postsParam: WidgetsParam): NetworkResponse<Widgets, APIErrorResponse<ErrorBody>> {
       return apiService.getWidgets(id, postsParam)
    }

}
