package com.example.digitoon.presentation.places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digitoon.data.remote.Resource
import com.example.digitoon.data.remote.entity.MoviesData
import com.example.digitoon.domain.usecase.movies.GetMoviesUseCase
import com.example.digitoon.presentation.util.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _moviesState = MutableStateFlow<BaseViewState<MoviesData>>(BaseViewState.Loading)
    val moviesState = _moviesState.asStateFlow()

    fun getMovies(){
        viewModelScope.launch {
            getMoviesUseCase.invoke().collect{ result ->
                when(result){
                    is Resource.Error -> {_moviesState.value = BaseViewState.ErrorString(result.toString())}
                    is Resource.Loading -> {_moviesState.value = BaseViewState.Loading}
                    is Resource.Success -> {
                        _moviesState.value = BaseViewState.Success(result.data)
                    }
                }
            }
        }
    }

}