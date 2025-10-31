package com.example.cloudscout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import com.example.cloudscout.viewmodel.MainViewModel
import com.example.cloudscout.util.getWeatherIconRes
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CurrentWeather(viewModel: MainViewModel) {

    val weather by viewModel.weather.collectAsState()
    val current = weather?.current
    val hourlyForecast = weather?.forecast?.forecastDay?.firstOrNull()?.hour ?: emptyList()
    val todayDate = weather?.forecast?.forecastDay?.firstOrNull()?.date ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (todayDate.isNotEmpty()) {
            Text(
                text = formatFullDate(todayDate),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        current?.let {
            Image(
                painter = painterResource(id = getWeatherIconRes(it.condition.text)),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(it.condition.text, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            Text("${it.temperature}°C", fontSize = 48.sp, fontWeight = FontWeight.Bold)
            Text("Feels like ${it.feelsLike}°C", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text("Wind ${it.windSpeed} kph", fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Hourly Forecast", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(hourlyForecast) { hour ->
                val formattedTime = formatTo12Hour(hour.time)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(formattedTime, fontSize = 14.sp) // e.g., "10 PM"
                    Image(
                        painter = painterResource(id = getWeatherIconRes(hour.condition.text)),
                        contentDescription = "Hourly Icon",
                        modifier = Modifier.size(40.dp)
                    )
                    Text("${hour.temperature}°", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

//Helper function for full date format
fun formatFullDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy, EEEE", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date ?: dateString)
    } catch (_: Exception) {
        dateString
    }
}

//Helper function for time format
fun formatTo12Hour(time: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("h a", Locale.getDefault()) // e.g., 10 PM
        val date = inputFormat.parse(time)
        outputFormat.format(date ?: time)
    } catch (_: Exception) {
        time // fallback if parsing fails
    }
}