package com.example.divartask.data.repository.posts

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.base.APIService
import com.example.divartask.data.network.ErrorBody
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import com.example.divartask.domain.repository.posts.PostsRepository
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val apiService: APIService
): PostsRepository {
    override suspend fun getPosts(id: Int, postsParam: PostsParam): NetworkResponse<PostsData, APIErrorResponse<ErrorBody>> {
       return apiService.getPosts(id, postsParam)
    }

}
