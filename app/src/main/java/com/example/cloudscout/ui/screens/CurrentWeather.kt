package com.example.cloudscout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cloudscout.viewmodel.MainViewModel

@Composable
fun CurrentWeather(navController: NavController, viewModel: MainViewModel = viewModel()) {
    val weather by viewModel.weather

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF87CEEB), Color(0xFFB0E0E6))))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = weather.icon),
                        contentDescription = "Weather Icon",
                        modifier = Modifier.size(120.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(weather.condition, fontSize = 28.sp, color = Color.Black)
                    Text(weather.temperature, fontSize = 36.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Precipitation: ${weather.precipitation}", fontSize = 16.sp)
                    Text("Wind: ${weather.wind}", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = { navController.navigate("forecast") },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("View Forecast")
            }
        }
    }
}
