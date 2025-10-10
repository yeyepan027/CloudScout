package com.example.cloudscout.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cloudscout.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Weather(
    val date: String = "",
    val condition: String,
    val temperature: Int,
    val feelsLike: Int,
    val windSpeed: String,
    val high: Int = 0,
    val low: Int = 0,
    val description: String = "",
    val icon: Int
)

class MainViewModel : ViewModel() {

    private val _currentWeather = MutableStateFlow(
        Weather(
            condition = "Overcast",
            temperature = 6,
            feelsLike = 2,
            windSpeed = "SW 18 kph",
            icon = R.drawable.currentweathericon
        )
    )
    val currentWeather: StateFlow<Weather> = _currentWeather.asStateFlow()

    private val _forecastList = MutableStateFlow(
        listOf(
            Weather(
                date = "Sunday, Oct 12",
                condition = "Rainy",
                temperature = 10,
                high = 10,
                low = 3,
                description = "Chance of rain 76%. Winds 41kph. Humidity 96%.",
                icon = R.drawable.newrainy_icon,
                feelsLike = 0,
                windSpeed = ""
            ),
            Weather(
                date = "Monday, Oct 13",
                condition = "Overcast",
                temperature = 15,
                high = 15,
                low = 3,
                description = "Overcast. Max winds 32kph. Humidity 84%.",
                icon = R.drawable.newcloudy_icon,
                feelsLike = 0,
                windSpeed = ""
            ),
            Weather(
                date = "Tuesday, Oct 14",
                condition = "Partly Cloudy",
                temperature = 7,
                high = 7,
                low = -1,
                description = "Partly Cloudy. Max winds 21kph. Humidity 89%.",
                icon = R.drawable.newsunny_icon,
                feelsLike = 0,
                windSpeed = ""
            )
        )
    )
    val forecastList: StateFlow<List<Weather>> = _forecastList.asStateFlow()
}