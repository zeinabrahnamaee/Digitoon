package com.example.divartask.data.entity


import com.google.gson.annotations.SerializedName

data class PostsData(
    @SerializedName("widget_list")
    val widgetList: List<Post>? = null
) {
    data class Post(
        @SerializedName("data")
        val data: DataModel? = null,
        @SerializedName("text")
        val text: String? = null,
        @SerializedName("widget_type")
        val widgetType: String? = null
    )
}

data class DataModel(
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("district")
    val district: String? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("subtitle")
    val subtitle: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("token")
    val token: String? = null
)