package com.example.cloudscout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cloudscout.viewmodel.MainViewModel
import com.example.cloudscout.util.getWeatherIconRes
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DailyForecast(viewModel: MainViewModel) {

    val weather by viewModel.weather.collectAsState()
    val forecastDays = weather?.forecast?.forecastDay ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(forecastDays) { day ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.6f) // darker semi-transparent background
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = formatDate(day.date),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "${day.day.maxTemp}°C High  ${day.day.minTemp}°C Low",
                                fontSize = 16.sp,
                                color = Color.White
                            )
                            Text(
                                text = day.day.condition.text,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Image(
                            painter = painterResource(id = getWeatherIconRes(day.day.condition.text)),
                            contentDescription = "Forecast Icon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
}

// Helper function to format date
fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy, EEEE", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date ?: dateString)
    } catch (_: Exception) {
        dateString
    }
}