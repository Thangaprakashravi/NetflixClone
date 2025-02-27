package com.example.netflixclone



import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Page1(navController: NavController) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })
    val coroutineScope = rememberCoroutineScope()

    val backgroundImages = listOf(
        R.drawable.netflix1,
        R.drawable.netflix2,
        R.drawable.netflix3,
        R.drawable.netflix4
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = rememberImagePainter(
                data = backgroundImages[pagerState.currentPage],
                builder = {
                    crossfade(true) // Smooth image transition
                }
            ),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark overlay for better visibility
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Pager for slides
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingContent(page)
            }

            Spacer(modifier = Modifier.height(16.dp)) // Added space for indicators

            // Page Indicator with Clickable Dots
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .size(if (pagerState.currentPage == index) 14.dp else 10.dp)
                            .background(if (pagerState.currentPage == index) Color.Red else Color.Gray)
                            .clickable {
                                coroutineScope.launch { pagerState.animateScrollToPage(index) }
                            }
                            .padding(6.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Spacing between indicators
                }
            }

            Spacer(modifier = Modifier.height(20.dp)) // Added space below indicators

            // Start Watching Button (only on the last slide)
            if (pagerState.currentPage == 3) {
                Button(
                    onClick = { navController.navigate("profile_selection") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start Watching", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun OnboardingContent(page: Int) {
    val titles = listOf("Welcome to Netflix", "Watch Anytime", "Download Offline", "Enjoy Everywhere")
    val descriptions = listOf(
        "Your favorite movies and TV shows in one place.",
        "Stream seamlessly on any device.",
        "Download and watch anytime, anywhere.",
        "Enjoy Netflix on TV, phone, tablet, and more."
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = titles[page],
            fontSize = 24.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = descriptions[page],
            fontSize = 16.sp,
            color = Color.LightGray
        )
    }
}