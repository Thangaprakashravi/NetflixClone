package com.example.netflixclone


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
fun SearchScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<Movie>>(emptyList()) }

    // Trigger search when query changes (you could debounce this in a real app)
    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            searchResults = MovieRepository.searchMovies(query)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Search Movies") })
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search") },
                modifier = Modifier.padding(8.dp)
            )
            LazyColumn {
                items(searchResults) { movie ->
                    SearchResultItem(movie = movie, onClick = {
                        navController.navigate("detail/${movie.id}")
                    })
                }
            }
        }
    }
}

@Composable
fun SearchResultItem(movie: Movie, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        Image(
            painter = rememberImagePainter(posterUrl),
            contentDescription = movie.title,
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
        )
        Text(text = movie.title, modifier = Modifier.padding(start = 8.dp))
    }
}
