package com.example.divartask.data.base

import com.example.divartask.data.entity.DetailData
import com.example.divartask.data.entity.PlacesListData
import com.example.divartask.data.entity.PostsData
import com.example.divartask.data.params.PostsParam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/api/v1/place/list")
    suspend fun getPlaces(): PlacesListData

    @POST("/api/v1/post/list")
    suspend fun getPosts(@Query("city") city: Int, @Body postsParam: PostsParam): PostsData

    @GET("/api/v1/post/view/{postToken}")
    suspend fun getDetail(@Path("postToken") token: String): DetailData
}