package com.example.divartask.presentation.widgets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.model.PlacesDomain
import com.example.divartask.domain.model.WidgetsDomain
import com.example.divartask.domain.usecase.usercity.GetUserLocUseCase
import com.example.divartask.domain.usecase.widgets.GetWidgetsUseCase
import com.example.divartask.presentation.util.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WidgetsViewModel @Inject constructor(
    private val postsUseCase: GetWidgetsUseCase,
    private val userLocationUseCase: GetUserLocUseCase,
) : ViewModel() {

    private var paging: Int = -1

    private val _widgetsState = MutableSharedFlow<BaseViewState<List<WidgetsDomain>>>(1)
    val widgetsState = _widgetsState.asSharedFlow()

    private val _locationState = MutableSharedFlow<BaseViewState<PlacesDomain>>(1)
    val locationState = _locationState.asSharedFlow()

    fun getWidgets(id: Int){
        paging ++
        viewModelScope.launch {
            postsUseCase.invoke(id, WidgetsParam(0, paging)).collect{
                when(it){
                    is Resource.Error -> {
                        _widgetsState.emit(BaseViewState.ErrorString(it.message))
                    }
                    is Resource.Loading -> {
                        _widgetsState.emit(BaseViewState.Loading)
                    }
                    is Resource.Success -> {
                        _widgetsState.emit(BaseViewState.Success(it.data))
                    }
                }
            }
        }
    }

    fun getUserCity(latitude: Double?, longitude: Double?) {
        viewModelScope.launch {
            userLocationUseCase.invoke(latitude, longitude).collect{
                when(it){
                    is Resource.Error -> {
                        _locationState.emit(BaseViewState.ErrorString(it.message))
                    }
                    is Resource.Loading -> {
                        _locationState.emit(BaseViewState.Loading)
                    }
                    is Resource.Success -> {
                        _locationState.emit(BaseViewState.Success(it.data))
                    }
                }
            }
        }
    }


}