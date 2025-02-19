package com.example.netflixclone


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.netflixclone.ui.theme.NetflixCloneTheme
import com.google.firebase.auth.FirebaseAuth


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetflixCloneTheme {
                val navController = rememberNavController()
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: "splash"

                Scaffold(
                    bottomBar = {
                        if (currentRoute !in listOf("splash", "login", "signup")) {
                            BottomNavigationBar(navController)
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(navController, startDestination = "splash") {
                            composable("splash") { SplashScreen(navController) }
                            composable("login") { LoginScreen(navController) }
                            composable("signup") { SignUpScreen(navController) }
                            composable("home") { HomeScreen(navController) }
                            composable("search") { SearchScreen(navController) }
                            composable("profile") { ProfileScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBar(navController: NavHostController){
    NavigationBar (
      containerColor = Color.Red
    ) {
        NavigationBarItem(
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = false,
            onClick = { navController.navigate("home") },
        )
        NavigationBarItem(
            label = { Text("Search") },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            selected = false,
            onClick = { navController.navigate("search") }
        )
        NavigationBarItem(
            label = { Text("Profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
    }
}


