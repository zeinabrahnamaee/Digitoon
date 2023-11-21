package com.example.divartask.domain.usecase.posts

import com.example.divartask.data.base.Resource
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import kotlinx.coroutines.flow.Flow

interface GetPostsUseCase {
    operator fun invoke(id: Int, postsParam: PostsParam): Flow<Resource<PostsData>>
}