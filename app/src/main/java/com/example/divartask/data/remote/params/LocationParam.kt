package com.example.divartask.data.remote.params


import com.google.gson.annotations.SerializedName

data class LocationParam(
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("long")
    val long: Double? = null
)