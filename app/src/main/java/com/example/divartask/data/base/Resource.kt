package com.example.divartask.data.base


sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    class Error<T>(message: String) : Resource<T>()
    class Loading<T> : Resource<T>()
}