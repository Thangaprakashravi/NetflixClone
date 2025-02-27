package com.example.netflixclone

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val results: List<MovieDto>
)

data class MovieDto(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String
)
