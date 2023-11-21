package com.example.divartask.domain.usecase.posts

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import com.example.divartask.domain.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPostsUseCaseImp @Inject constructor(
    private val repository: PostsRepository
): GetPostsUseCase {
    override fun invoke(id: Int, postsParam: PostsParam): Flow<Resource<PostsData>> = flow {
        repository.getPosts(id, postsParam).collect{
            emit(it)
        }
    }
}