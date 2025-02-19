package com.example.netflixclone

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String,
    val vote_average: Double,
    val release_date: String,
    val genre: String
)

data class MovieResponse(
    val results: List<Movie>)