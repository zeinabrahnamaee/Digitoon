package com.example.divartask.data.repository.posts

import com.example.divartask.data.base.APIService
import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import com.example.divartask.domain.repository.posts.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val apiService: APIService
): PostsRepository {
    override suspend fun getPosts(id: Int, postsParam: PostsParam): Flow<Resource<PostsData>> = flow{
       emit(Resource.Success(apiService.getPosts(id, postsParam)))
    }

}
