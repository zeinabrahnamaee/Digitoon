package com.example.divartask.data.remote.network


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("message")
    val message: MessageData? = null,
    @SerializedName("success")
    val isSuccess: Boolean = false,
)

data class MessageData(
    @SerializedName("title")
    val errorMessage: String? = null,
)
