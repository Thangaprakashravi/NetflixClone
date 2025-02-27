package com.example.netflixclone

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    profileName: String,
    navController: NavController,
    profileAvatar: String
) {
    val movies by viewModel.movies.collectAsState()
    val pagerState = rememberPagerState(pageCount = { 7 })

    LaunchedEffect(Unit) { viewModel.fetchMovies("bb387243be7d4bb7548e03e0fdd2d5e6") }

    Column {
        HorizontalPager(state = pagerState, modifier = Modifier.height(200.dp)) { page ->
            movies.getOrNull(page)?.let { movie ->
                Image(painter = rememberImagePainter("https://image.tmdb.org/t/p/w500${movie.posterPath}"), contentDescription = null)
            }
        }

        LazyColumn {
            items(movies) { movie ->
                Text(text = movie.title)
            }
        }
    }
}