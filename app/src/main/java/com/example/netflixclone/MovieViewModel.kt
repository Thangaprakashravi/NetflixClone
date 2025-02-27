package com.example.netflixclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    val movies = repository.getMovies().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun fetchMovies(apiKey: String, genreId: String? = null, query: String? = null) {
        viewModelScope.launch {
            repository.fetchMovies(apiKey, genreId, query)
        }
    }
}