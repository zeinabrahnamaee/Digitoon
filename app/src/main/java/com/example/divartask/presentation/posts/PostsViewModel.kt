package com.example.divartask.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import com.example.divartask.domain.usecase.posts.GetPostsUseCase
import com.example.divartask.presentation.util.BaseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCase: GetPostsUseCase
) : ViewModel() {

    private var paging: Int = -1

    private val _postsState = MutableStateFlow<BaseViewState<PostsData>>(BaseViewState.Loading)
    val postsState = _postsState.asStateFlow()

    fun getPosts(id: Int){
        paging ++
        viewModelScope.launch {
            postsUseCase.invoke(id, PostsParam(0, paging)).collect{
                when(it){
                    is Resource.Error -> {
                        _postsState.value = BaseViewState.ErrorString(it.message)
                    }
                    is Resource.Loading -> {
                        _postsState.value = BaseViewState.Loading
                    }
                    is Resource.Success -> {
                        if(it.data.widgetList?.isNotEmpty() == true)
                        _postsState.value = BaseViewState.Success(it.data)
                    }
                }
            }
        }
    }



}