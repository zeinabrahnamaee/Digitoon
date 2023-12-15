package com.example.digitoon.data.remote.entity


import com.google.gson.annotations.SerializedName

data class MoviesData(
    @SerializedName("Response")
    val response: String? = null,
    @SerializedName("Search")
    val search: List<Search>? = null,
    @SerializedName("totalResults")
    val totalResults: String? = null
) {
    data class Search(
        @SerializedName("imdbID")
        val imdbID: String? = null,
        @SerializedName("Poster")
        val poster: String? = null,
        @SerializedName("Title")
        val title: String? = null,
        @SerializedName("Type")
        val type: String? = null,
        @SerializedName("Year")
        val year: String? = null
    )
}