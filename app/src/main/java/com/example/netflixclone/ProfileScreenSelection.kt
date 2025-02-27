package com.example.netflixclone

import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter




@Composable
fun ProfileScreenSelection(navController: NavController) {
    val profiles = listOf(
        Pair("User 1", R.drawable.vecteezy_modern_male_avatar_with_black_hair_and_hoodie_illustration_48216761),
        Pair("User 2", R.drawable.vecteezy_cute_girl_watercolor_23257947),
        Pair("User 3", R.drawable.vecteezy_ai_generated_a_girl_in_a_hoodie_and_backpack_36706533)
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black).verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp)) // Space from top

        Text(
            "Who's watching?",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp)) // Space before avatars

        // Display profiles + "Add Profile" in a 2x2 grid
        val allProfiles = profiles + Pair("Add Profile", null) // Add Profile button included
        val rows = allProfiles.chunked(2) // Split into rows with 2 items each

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            rows.forEach { rowProfiles ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    rowProfiles.forEach { (name, avatar) ->
                        ProfileAvatar(name, avatar) {
                            if (avatar != null) {
                                // Navigate to home screen when user profile is selected
                                navController.navigate("home/${Uri.encode(name)}/${Uri.encode(avatar.toString())}") {
                                    popUpTo("profile_selection")
                                }
                            } else {
                                // Navigate to "Add Profile" screen
                                navController.navigate("login")
                            }
                        }
                    }
                }
            }
        }
    }
}

// Reusable Avatar Composable (Circle Shape + Clickable)
@Composable
fun ProfileAvatar(name: String, avatar: Int?, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
            .clickable { onClick() }
    ) {
        if (avatar != null) {
            Image(
                painter = painterResource(id = avatar),
                contentDescription = name,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            // "Add Profile" Button (Circle with + icon)
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Profile",
                    tint = Color.White,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(name, color = Color.White, fontSize = 16.sp)
    }
}
