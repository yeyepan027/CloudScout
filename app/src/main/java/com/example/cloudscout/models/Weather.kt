package com.example.cloudscout.models

data class Weather(
    val current: Current,
    val daily: List<Forecast>
)

data class Current(
    val temperature: Double,
    val description: String,
    val humidity: Int,
    val windSpeed: Double,
    val feelsLike: Double
)

data class Forecast(
    val date: String,
    val minTemp: Double,
    val maxTemp: Double,
    val description: String,
    val iconUrl: String? = null
)
