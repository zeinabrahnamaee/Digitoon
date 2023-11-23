package com.example.divartask.domain.usecase.posts

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.PostsData
import com.example.divartask.data.remote.params.PostsParam
import com.example.divartask.domain.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCaseImp @Inject constructor(
    private val repository: PostsRepository
): GetPostsUseCase {
    override fun invoke(id: Int, postsParam: PostsParam): Flow<Resource<PostsData>> = flow {
        val result = repository.getPosts(id, postsParam)
        when(result){
            is NetworkResponse.APIError -> {
                emit(Resource.Error((result.apiErrorResponse as APIErrorResponse.NotFoundResponse).error.message?.errorMessage?:""))
            }
            is NetworkResponse.Empty -> {}
            is NetworkResponse.NetworkError -> {
                emit(Resource.Error(result.exception.toString()))
            }
            is NetworkResponse.ProtocolError -> {
                emit(Resource.Error(result.exception.toString()))
            }
            is NetworkResponse.Success -> {
                emit(Resource.Success(result.body))
            }
            is NetworkResponse.UnknownError -> {

            }
        }
    }
}