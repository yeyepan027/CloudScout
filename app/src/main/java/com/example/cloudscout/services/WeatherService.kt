package com.example.cloudscout.services

import com.example.cloudscout.models.Weather
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") q: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no"
    ): Weather
}