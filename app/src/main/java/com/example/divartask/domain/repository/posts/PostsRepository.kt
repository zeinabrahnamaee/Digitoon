package com.example.divartask.domain.repository.posts

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    suspend fun getPosts(id: Int, postsParam: PostsParam): Flow<Resource<PostsData>>
}