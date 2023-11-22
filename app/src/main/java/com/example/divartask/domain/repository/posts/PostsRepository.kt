package com.example.divartask.domain.repository.posts

import com.example.divartask.data.network.APIErrorResponse
import com.example.divartask.data.network.ErrorBody
import com.example.divartask.data.network.NetworkResponse
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam

interface PostsRepository {
    suspend fun getPosts(id: Int, postsParam: PostsParam): NetworkResponse<PostsData, APIErrorResponse<ErrorBody>>
}