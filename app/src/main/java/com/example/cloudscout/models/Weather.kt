package com.example.cloudscout.models

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: ForecastContainer
)

data class Location(
    val name: String,
    val region: String,
    val country: String

)

data class ForecastContainer(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<HourlyForecast>
)

data class Day(
    @SerializedName("maxtemp_c") val maxTemp: Double,
    @SerializedName("mintemp_c") val minTemp: Double,
    val condition: Condition
)

data class HourlyForecast(
    val time: String,
    @SerializedName("temp_c") val temperature: Double,
    @SerializedName("feelslike_c") val feelsLike: Double,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("wind_kph") val windSpeed: Double,
    @SerializedName("gust_kph") val gustSpeed: Double,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("precip_mm") val precipitationMm: Double,
    @SerializedName("dewpoint_c") val dewPoint: Double,
    val uv: Double,
    @SerializedName("short_rad") val shortwaveRadiation: Double,
    @SerializedName("diff_rad") val diffuseRadiation: Double,
    val dni: Double,
    val gti: Double,
    val condition: Condition
)

data class Current(
    @SerializedName("temp_c") val temperature: Double,
    val humidity: Int,
    @SerializedName("wind_kph") val windSpeed: Double,
    @SerializedName("feelslike_c") val feelsLike: Double,
    val condition: Condition
)

data class Condition(
    val text: String,
    val icon: String
)