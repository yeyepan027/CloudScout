package com.example.cloudscout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cloudscout.viewmodel.MainViewModel
import com.example.cloudscout.viewmodel.Weather

@Composable
fun CurrentWeather(navController: NavHostController, viewModel: MainViewModel) {
    val weather: Weather by viewModel.currentWeather.collectAsState()

    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = weather.icon),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(48.dp))

            Text(weather.condition, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text("${weather.temperature}°C", fontSize = 48.sp, fontWeight = FontWeight.Bold)
            Text("Feels like ${weather.feelsLike}°C", fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text("Wind ${weather.windSpeed}", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
