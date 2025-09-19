package com.example.cloudscout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cloudscout.ui.screens.CurrentWeather
import com.example.cloudscout.ui.screens.DailyForecast
import com.example.cloudscout.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { CurrentWeather(navController, mainViewModel) }
                composable("forecast") { DailyForecast(navController, mainViewModel) }
            }
        }
    }
}
