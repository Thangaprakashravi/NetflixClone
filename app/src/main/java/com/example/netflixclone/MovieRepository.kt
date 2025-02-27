package com.example.netflixclone

import com.google.android.exoplayer2.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: MovieApiService,
    private val movieDao: MovieDao
) {
    fun getMovies(): Flow<List<Movie>> = movieDao.getMovies()

    suspend fun fetchMovies(apiKey: String, genreId: String? = null, query: String? = null) {
        try {
            val response = when {
                query != null -> apiService.searchMovies(apiKey, query)
                genreId != null -> apiService.getMoviesByGenre(apiKey, genreId)
                else -> apiService.getPopularMovies(apiKey)
            }
            val movies = response.results.map { movieDto ->
                Movie(id = movieDto.id, title = movieDto.title, posterPath = movieDto.posterPath)
            }
            movieDao.insertMovies(movies)
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error fetching movies", e)
        }
    }
}