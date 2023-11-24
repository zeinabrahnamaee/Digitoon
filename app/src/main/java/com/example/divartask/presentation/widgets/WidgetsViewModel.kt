package com.example.divartask.presentation.widgets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.params.WidgetsParam
import com.example.divartask.domain.usecase.widgets.GetWidgetsUseCase
import com.example.divartask.presentation.util.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WidgetsViewModel @Inject constructor(
    private val postsUseCase: GetWidgetsUseCase
) : ViewModel() {

    private var paging: Int = -1

    private val _widgetsState = MutableStateFlow<BaseViewState<Widgets>>(BaseViewState.Loading)
    val widgetsState = _widgetsState.asStateFlow()

    fun getWidgets(id: Int){
        paging ++
        viewModelScope.launch {
            postsUseCase.invoke(id, WidgetsParam(0, paging)).collect{
                when(it){
                    is Resource.Error -> {
                        _widgetsState.value = BaseViewState.ErrorString(it.message)
                    }
                    is Resource.Loading -> {
                        _widgetsState.value = BaseViewState.Loading
                    }
                    is Resource.Success -> {
                        if(it.data.widgetList?.isNotEmpty() == true)
                        _widgetsState.value = BaseViewState.Success(it.data)
                    }
                }
            }
        }
    }



}