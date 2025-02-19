package com.example.netflixclone


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

import kotlinx.coroutines.delay



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var popularMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var trendingMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var topRatedMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var actionMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var horrorMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var romanceMovies by remember { mutableStateOf<List<Movie>>(emptyList()) }

    LaunchedEffect(Unit) {
        popularMovies = MovieRepository.getPopularMovies()
        trendingMovies = MovieRepository.getTrendingMovies()
        topRatedMovies = MovieRepository.getTopRatedMovies()
        actionMovies = MovieRepository.getActionMovies()
        horrorMovies = MovieRepository.getHorrorMovies()
        romanceMovies = MovieRepository.getRomanceMovies()
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Netflix", color = Color.Red) })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            item {
                BannerSlider(trendingMovies)
            }

            // Movie Sections
            item { Section("Popular Movies", popularMovies, navController) }
            item { Section("Trending Movies", trendingMovies, navController) }
            item { Section("Top Rated Movies", topRatedMovies, navController) }
            item { Section("Action Movies", actionMovies, navController) }
            item { Section("Horror Movies", horrorMovies, navController) }
            item { Section("Romance Movies", romanceMovies, navController) }
        }
    }
}




@Composable
fun BannerSlider(movies: List<Movie>) {
    var currentIndex by remember { mutableStateOf(0) }
    val displayedMovies = movies.take(7)

    // Auto-slide effect (every 10 seconds)
    LaunchedEffect(movies) {
        while (movies.isNotEmpty()) {
            delay(3_000) // Wait for 10 seconds
            currentIndex = (currentIndex + 1) % displayedMovies.size
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            movies.getOrNull(currentIndex)?.let { movie ->
                val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"

                Image(
                    painter = rememberImagePainter(imageUrl),
                    contentDescription = movie.title,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                // Movie Title Overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = movie.title,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier

                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    }

    // Indicator Dots
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(displayedMovies.size) { index ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(if (index == currentIndex) Color.White else Color.Gray)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}


@Composable
fun Section(title: String, movies: List<Movie>, navController: NavController) {
    Text(text = title, modifier = Modifier.padding(8.dp))
    LazyRow {
        items(movies) { movie ->
            MovieItem(movie = movie, onClick = {
                navController.navigate("detail/${movie.id}")
            })
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        Image(
            painter = rememberImagePainter(posterUrl),
            contentDescription = movie.title,
            modifier = Modifier
                .width(120.dp)
                .height(180.dp),
        )
        Text(text = movie.title, modifier = Modifier.padding(top = 4.dp))
    }
}

