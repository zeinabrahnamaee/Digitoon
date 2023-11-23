package com.example.divartask.domain.repository.posts

import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.entity.PostsData
import com.example.divartask.data.remote.params.PostsParam

interface PostsRepository {
    suspend fun getPosts(id: Int, postsParam: PostsParam): NetworkResponse<PostsData, APIErrorResponse<ErrorBody>>
}