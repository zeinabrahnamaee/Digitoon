package com.example.divartask.data.params


import com.google.gson.annotations.SerializedName

data class PostsParam(
    @SerializedName("last_post_date")
    val lastPostDate: Int? = null,
    @SerializedName("page")
    val page: Int? = null
)