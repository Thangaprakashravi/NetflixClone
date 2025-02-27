package com.example.netflixclone

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun SearchScreen(navController: NavController,viewModel: MovieViewModel = hiltViewModel()) {
    var query by remember { mutableStateOf("") }
    val movies by viewModel.movies.collectAsState()

    Column {
        TextField(value = query, onValueChange = {
            query = it
            viewModel.fetchMovies("bb387243be7d4bb7548e03e0fdd2d5e6", query = query)
        })
        LazyColumn {
            items(movies) { movie ->
                Text(text = movie.title)
            }
        }
    }
}