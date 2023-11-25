package com.example.divartask.data.remote

import com.example.divartask.data.remote.entity.DetailData
import com.example.divartask.data.remote.entity.PlacesListData
import com.example.divartask.data.remote.entity.UserLocationData
import com.example.divartask.data.remote.entity.Widgets
import com.example.divartask.data.remote.network.APIErrorResponse
import com.example.divartask.data.remote.network.ErrorBody
import com.example.divartask.data.remote.network.NetworkResponse
import com.example.divartask.data.remote.params.LocationParam
import com.example.divartask.data.remote.params.WidgetsParam
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface APIService {
    @GET("/api/v1/place/list")
    suspend fun getPlaces(): NetworkResponse<PlacesListData, APIErrorResponse<ErrorBody>>

    @POST("/api/v1/post/list")
    suspend fun getWidgets(@Query("city") city: Int, @Body postsParam: WidgetsParam): NetworkResponse<Widgets, APIErrorResponse<ErrorBody>>

    @GET("/api/v1/post/view/{postToken}")
    suspend fun getDetail(@Path("postToken") token: String): NetworkResponse<DetailData, APIErrorResponse<ErrorBody>>

    @POST("/api/v1/place/find")
    suspend fun getUserLocation(@Body locParam: LocationParam): NetworkResponse<UserLocationData, APIErrorResponse<ErrorBody>>
}