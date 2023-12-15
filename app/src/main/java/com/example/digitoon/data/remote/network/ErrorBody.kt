package com.example.digitoon.data.remote.network


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("Error")
    val errorMessage: String? = null,
    @SerializedName("Response")
    val response: Boolean = false,
)