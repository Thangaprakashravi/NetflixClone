package com.example.netflixclone



import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileScreen(navController: NavController) {

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding( 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .clickable { imagePickerLauncher.launch("image/*") }
        ) {
            selectedImageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            } ?: Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Default Profile",
                modifier = Modifier.size(80.dp),
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(70.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Name:", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("Thangapraksh", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Email:", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("thangaprakash@gmail.com", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Mobile:", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("8098336141", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Date of Birth:", color = Color.Gray, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text("20/11/2003", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Light)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth().padding(40.dp), shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign Out ", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}