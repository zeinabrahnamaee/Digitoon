package com.example.divartask.domain.usecase.widgets

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.model.WidgetsDomain
import com.example.divartask.domain.repository.widgets.WidgetsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWidgetsUseCaseImp @Inject constructor(
    private val repository: WidgetsRepository
): GetWidgetsUseCase {
    override suspend fun invoke(id: Int, postsParam: WidgetsParam): Flow<Resource<List<WidgetsDomain>>> = repository.getWidgets(id, postsParam)
}