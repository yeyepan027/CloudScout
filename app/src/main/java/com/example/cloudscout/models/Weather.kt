package com.example.cloudscout.models

data class Weather(
    val current: Current,          // current weather data
    val daily: List<Forecast>      // list of daily forecasts
)

data class Current(
    val temperature: Double,       // current temperature in °C
    val description: String,       // weather condition e.g., "Sunny", "Cloudy"
    val humidity: Int,             // humidity percentage
    val windSpeed: Double,         // wind speed in km/h or m/s
    val feelsLike: Double          // "feels like" temperature
)

data class Forecast(
    val date: String,              // date of forecast e.g., "2025-09-19"
    val minTemp: Double,           // minimum temperature
    val maxTemp: Double,           // maximum temperature
    val description: String,       // e.g., "Rainy", "Partly Cloudy"
    val iconUrl: String? = null    // optional icon for weather
)
