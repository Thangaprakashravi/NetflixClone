package com.example.netflixclone

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("api_key") apiKey: String, @Query("with_genres") genreId: String): MovieResponse

    @GET("search/movie")
    suspend fun searchMovies(@Query("api_key") apiKey: String, @Query("query") query: String): MovieResponse
}