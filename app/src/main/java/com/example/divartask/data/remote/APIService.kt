package com.example.divartask.data.remote

import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.data.remote.entity.PostsData
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.params.PostsParam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/api/v1/place/liste")
    suspend fun getPlaces(): NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>>

    @POST("/api/v1/post/list")
    suspend fun getPosts(@Query("city") city: Int, @Body postsParam: PostsParam): NetworkResponse<PostsData, APIErrorResponse<ErrorBody>>

    @GET("/api/v1/post/view/{postToken}")
    suspend fun getDetail(@Path("postToken") token: String): NetworkResponse<DetailData, APIErrorResponse<ErrorBody>>
}