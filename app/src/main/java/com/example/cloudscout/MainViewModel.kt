package com.example.cloudscout.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cloudscout.R

// Current weather
data class Weather(
    val condition: String,
    val temperature: String,
    val precipitation: String,
    val wind: String,
    val icon: Int
)

// Daily forecast
data class DailyForecast(
    val date: String,
    val icon: Int,
    val tempHigh: String,
    val tempLow: String,
    val condition: String,
    val precipitation: String,
    val wind: String,
    val humidity: String
)

class MainViewModel : ViewModel() {

    val weather = mutableStateOf(
        Weather(
            condition = "Sunny",
            temperature = "30°C",
            precipitation = "0mm",
            wind = "NE 10 km/h",
            icon = R.drawable.sun_icon
        )
    )

    val forecast = listOf(
        DailyForecast("Mon", R.drawable.cloud_icon, "28°C", "22°C", "Cloudy", "2mm, 20%", "NE 12 km/h", "60%"),
        DailyForecast("Tue", R.drawable.rain_icon, "29°C", "21°C", "Rainy", "5mm, 40%", "E 15 km/h", "70%"),
        DailyForecast("Wed", R.drawable.sun_icon, "31°C", "23°C", "Sunny", "0mm, 0%", "N 8 km/h", "50%")
    )
}
