package com.example.cloudscout.util

import com.example.cloudscout.R

fun getWeatherIconRes(conditionText: String): Int {
    val text = conditionText.lowercase()

    return when {
        "sunny" in text -> R.drawable.newsunny_icon
        "partly cloudy" in text -> R.drawable.newcloudy_icon
        "cloudy" in text -> R.drawable.cloudy
        "overcast" in text -> R.drawable.overcast
        "mist" in text -> R.drawable.mist
        "clear" in text -> R.drawable.clear
        "patchy rain" in text -> R.drawable.patchyrain
        "rain" in text -> R.drawable.newrainy_icon
        "snow" in text -> R.drawable.snow
        "thunder" in text -> R.drawable.storm
        else -> R.drawable.unknweather
    }
}