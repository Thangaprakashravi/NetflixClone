package com.example.netflixclone


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movieId: String?, navController: NavController) {
    var movie by remember { mutableStateOf<Movie?>(null) }

    LaunchedEffect(movieId) {
        movieId?.toIntOrNull()?.let { id ->
            // For a real app, call a dedicated "movie details" endpoint.
            // Here we search in the popular movies list for simplicity.
            val popularMovies = MovieRepository.getPopularMovies()
            movie = popularMovies.find { it.id == id }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(movie?.title ?: "Movie Detail") })
        }
    ) { paddingValues ->
        movie?.let {
            Column(modifier = Modifier.padding(paddingValues)) {
                val backdropUrl = "https://image.tmdb.org/t/p/w500${it.backdrop_path}"
                Image(
                    painter = rememberImagePainter(backdropUrl),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Text(text = it.title, modifier = Modifier.padding(8.dp))
                Text(text = it.overview, modifier = Modifier.padding(8.dp))
            }
        } ?: run {
            Text(text = "Loading...", modifier = Modifier.padding(16.dp))
        }
    }
}
