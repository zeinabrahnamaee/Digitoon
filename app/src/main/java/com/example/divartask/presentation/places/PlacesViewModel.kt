package com.example.divartask.presentation.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.domain.usecase.places.GetPlacesUseCase
import com.example.divartask.presentation.util.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlacesViewModel @Inject constructor(
    private val getPlacesUseCase: GetPlacesUseCase
) : ViewModel() {

    private val _placesState = MutableStateFlow<BaseViewState<PlacesListData>>(BaseViewState.Loading)
    val placesState = _placesState.asStateFlow()

    fun getPlaces(){
        viewModelScope.launch {
            getPlacesUseCase.invoke().collect{ result ->
                when(result){
                    is Resource.Error -> {_placesState.value = BaseViewState.ErrorString(result.toString())}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        _placesState.value = BaseViewState.Success(result.data)
                    }
                }
            }
        }
    }

}