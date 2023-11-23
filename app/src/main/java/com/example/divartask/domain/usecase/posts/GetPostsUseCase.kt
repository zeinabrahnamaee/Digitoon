package com.example.divartask.domain.usecase.posts

import com.example.divartask.data.remote.Resource
import com.example.divartask.data.remote.entity.PostsData
import com.example.divartask.data.remote.params.PostsParam
import kotlinx.coroutines.flow.Flow

interface GetPostsUseCase {
    operator fun invoke(id: Int, postsParam: PostsParam): Flow<Resource<PostsData>>
}