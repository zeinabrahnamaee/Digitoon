package com.example.divartask.data.repository.detail

import com.example.divartask.data.database.dao.DetailDao
import com.example.divartask.data.database.entity.DetailEntity
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.domain.repository.detail.DetailRepository
import com.example.divartask.presentation.util.convertToDetailsDomainModel
import com.example.divartask.presentation.util.convertToDomainModel
import com.example.divartask.presentation.util.saveList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepositoryImp @Inject constructor(
    private val apiService: APIService,
    private val dao: DetailDao
): DetailRepository {
    override suspend fun getDetail(token: String): Flow<Resource<List<DetailDomain>>> = flow<Resource<List<DetailDomain>>> {
        when(val result = apiService.getDetail(token)){
            is NetworkResponse.APIError -> {
                emit(Resource.Error((result.apiErrorResponse as APIErrorResponse.NotFoundResponse).error.message?.errorMessage?:""))
            }
            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(result.exception.message.toString()))
            }
            is NetworkResponse.Success -> {
                val convertedData = result.body.convertToDomainModel()
                dao.insertDetailData(convertToEntities(convertedData, token))
            }
            else -> {
                emit(Resource.Error("error in network"))
            }
        }
        emit(Resource.Success(dao.getDetail(token).convertToDetailsDomainModel()))
    }.flowOn(Dispatchers.IO)

    private fun convertToEntities(result: List<DetailDomain>, token: String): List<DetailEntity> {
        val detailViewList: ArrayList<DetailEntity> = arrayListOf()
        result.forEach {
            detailViewList.add(
                DetailEntity(
                    it.widgetType?:"",
                    saveList(it.items),
                    it.subtitle,
                    it.text,
                    it.value,
                    it.title,
                    token
                )
            )
        }
        return detailViewList
    }

}
