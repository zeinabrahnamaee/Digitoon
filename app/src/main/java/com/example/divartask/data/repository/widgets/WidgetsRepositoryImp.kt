package com.example.divartask.data.repository.widgets

import com.example.divartask.data.database.dao.WidgetsDao
import com.example.divartask.data.database.entity.WidgetsEntity
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.model.WidgetsDomain
import com.example.divartask.domain.repository.widgets.WidgetsRepository
import com.example.divartask.presentation.util.convertToWidgetsDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WidgetsRepositoryImp @Inject constructor(
    private val apiService: APIService,
    private val dao: WidgetsDao
): WidgetsRepository {
    override suspend fun getWidgets(id: Int, widgetsParam: WidgetsParam): Flow<Resource<List<WidgetsDomain>>> = flow<Resource<List<WidgetsDomain>>> {

        val dbCount = dao.getCount(id)
        when(val result = apiService.getWidgets(id, widgetsParam)){
            is NetworkResponse.APIError -> {
                emit(Resource.Error((result.apiErrorResponse as APIErrorResponse.NotFoundResponse).error.message?.errorMessage?:""))
            }
            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(result.exception.message.toString()))
            }
            is NetworkResponse.Success -> {
                dao.insertWidgets(convertToEntities(id, result))
            }
            else -> {
                emit(Resource.Error("error in network"))
            }
        }
        val list = dao.getWidgets(id).convertToWidgetsDomainModel()
        val subList = if (widgetsParam.page != 0) list.subList(dbCount, list.size).toList() else list
        emit(Resource.Success(subList))
    }.flowOn(Dispatchers.IO)

    private fun convertToEntities(id: Int, result: NetworkResponse<Widgets, APIErrorResponse<ErrorBody>>): List<WidgetsEntity>{
        val widgets: ArrayList<WidgetsEntity> = arrayListOf()
        (result as NetworkResponse.Success).body.widgetList?.forEach {
            widgets.add(
                WidgetsEntity(
                    id,
                    it.widgetType?:"",
                    it.data?.text?:"",
                    it.data?.subtitle?:"",
                    it.data?.district?:"",
                    it.data?.price?:"",
                    it.data?.thumbnail?:"",
                    it.data?.title?:"",
                    it.data?.token?:"",
                )
            )
        }
        return widgets
    }

}
