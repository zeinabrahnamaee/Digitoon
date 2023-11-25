package com.example.divartask.data.remote.entity


import com.google.gson.annotations.SerializedName

data class UserLocationData(
    @SerializedName("centroid")
    val centroid: Centroid? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("radius")
    val radius: Int? = null,
    @SerializedName("slug")
    val slug: String? = null
) {
    data class Centroid(
        @SerializedName("latitude")
        val latitude: Double? = null,
        @SerializedName("longitude")
        val longitude: Double? = null
    )
}