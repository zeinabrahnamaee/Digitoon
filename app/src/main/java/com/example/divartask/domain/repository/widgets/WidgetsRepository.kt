package com.example.divartask.domain.repository.widgets

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.data.remote.entity.UserLocationData
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.model.WidgetsDomain
import kotlinx.coroutines.flow.Flow

interface WidgetsRepository {
    suspend fun getWidgets(id: Int, postsParam: WidgetsParam): Flow<Resource<List<WidgetsDomain>>>
    suspend fun getUserLocation(lat: Double?, long: Double?): Flow<Resource<PlacesDomain>>
}