package com.example.divartask.domain.usecase.widgets

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.model.WidgetsDomain
import kotlinx.coroutines.flow.Flow

interface GetWidgetsUseCase {
    suspend operator fun invoke(id: Int, postsParam: WidgetsParam): Flow<Resource<List<WidgetsDomain>>>
}