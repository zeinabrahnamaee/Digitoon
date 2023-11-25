package com.example.divartask.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.domain.model.DetailDomain
import com.example.divartask.domain.usecase.detail.GetDetailUseCase
import com.example.divartask.presentation.util.BaseViewState
import com.example.divartask.presentation.util.convertToDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: GetDetailUseCase
) : ViewModel() {

    private val _detailState = MutableStateFlow<BaseViewState<List<DetailDomain>>>(BaseViewState.Loading)
    val detailState = _detailState.asStateFlow()

    fun getDetail(token: String){
        viewModelScope.launch {
            detailUseCase.invoke(token).collect{
                when(it){
                    is Resource.Error -> { _detailState.value = BaseViewState.ErrorString(it.message)}
                    is Resource.Loading -> { _detailState.value = BaseViewState.Loading }
                    is Resource.Success -> { _detailState.value = BaseViewState.Success(it.data) }
                }
            }
        }
    }
}