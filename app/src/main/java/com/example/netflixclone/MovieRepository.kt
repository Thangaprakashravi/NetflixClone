package com.example.netflixclone


object MovieRepository {
    private const val API_KEY = "bb387243be7d4bb7548e03e0fdd2d5e6"

    suspend fun getPopularMovies() = RetrofitInstance.api.getPopularMovies(API_KEY).results

    suspend fun getTrendingMovies() = RetrofitInstance.api.getTrendingMovies(API_KEY).results

    suspend fun getTopRatedMovies() = RetrofitInstance.api.getTopRatedMovies(API_KEY).results

    suspend fun getActionMovies() = RetrofitInstance.api.getMoviesByGenre(API_KEY, "28").results
    suspend fun getHorrorMovies() = RetrofitInstance.api.getMoviesByGenre(API_KEY, "27").results
    suspend fun getRomanceMovies() = RetrofitInstance.api.getMoviesByGenre(API_KEY, "10749").results

    suspend fun searchMovies(query: String) = RetrofitInstance.api.searchMovies(API_KEY, query).results
}
