package com.example.cloudscout
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cloudscout.ui.screens.CurrentWeather
import com.example.cloudscout.ui.screens.DailyForecast
import com.example.cloudscout.viewmodel.MainViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CloudScout(mainViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloudScout(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf("Today") }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
        systemUiController.setNavigationBarColor(Color.Transparent, darkIcons = false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                LargeTopAppBar(
                    title = { Text("Halifax, Nova Scotia") },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color.Transparent,

                    )
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = Color.Transparent
                ) {
                    NavigationBarItem(
                        selected = selectedTab == "Today",
                        onClick = {
                            selectedTab = "Today"
                            navController.navigate("Today") {
                                popUpTo("Today") { inclusive = true }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.WbSunny,
                                contentDescription = "Today",
                                tint = Color.White
                            )
                        },
                        label = {
                            Text("Today", color = Color.White)
                        },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                indicatorColor = Color.Transparent, // removes the pink background
                                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                                unselectedTextColor = Color.White.copy(alpha = 0.6f)

                            )
                    )
                    NavigationBarItem(
                        selected = selectedTab == "daily",
                        onClick = {
                            selectedTab = "daily"
                            navController.navigate("daily") {
                                popUpTo("Today") { inclusive = false }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.CalendarToday,
                                contentDescription = "Daily",
                                tint = Color.White
                            )
                        },
                        label = {
                            Text("Daily", color = Color.White)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = Color.White,
                            indicatorColor = Color.Transparent,
                        )
                    )
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "Today",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("Today") { CurrentWeather(navController, mainViewModel) }
                composable("daily") { DailyForecast(navController, mainViewModel) }
            }
        }
    }
}
