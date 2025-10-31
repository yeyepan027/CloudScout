package com.example.cloudscout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cloudscout.models.Weather
import com.example.cloudscout.services.WeatherService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> = _weather

    val weatherService: WeatherService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = weatherService.getForecast(
                    apiKey = "75a27079e4e34e4ba26113426251710",
                    q = "Halifax",
                    days = 7,
                    aqi = "no",
                    alerts = "no"
                )
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}