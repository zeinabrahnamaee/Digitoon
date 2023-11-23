package com.example.divartask.data.repository.posts

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.APIService
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.PostsData
import com.example.divartask.data.remote.params.PostsParam
import com.example.divartask.domain.repository.posts.PostsRepository
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val apiService: APIService
): PostsRepository {
    override suspend fun getPosts(id: Int, postsParam: PostsParam): NetworkResponse<PostsData, APIErrorResponse<ErrorBody>> {
       return apiService.getPosts(id, postsParam)
    }

}
