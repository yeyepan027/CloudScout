package com.example.cloudscout
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cloudscout.ui.screens.CurrentWeather
import com.example.cloudscout.ui.screens.DailyForecast
import com.example.cloudscout.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource


class MainActivity : ComponentActivity() {
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainViewModel.fetchWeather()
        setContent {
            GetLocation()
            CloudScout(mainViewModel)
        }
    }
    @OptIn(ExperimentalPermissionsApi::class)
    @Composable
    fun GetLocation() {
        // Remember the permission state(asking for Fine location)
        val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionState.status.isGranted) {
            Log.i("TESTING", "Hurray, permission granted!")

            // Get Location
            val currentContext = LocalContext.current
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(currentContext)

            if (ContextCompat.checkSelfPermission(
                    currentContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
            {
                val cancellationTokenSource = CancellationTokenSource()

                Log.i("TESTING", "Requesting location...")

                fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, cancellationTokenSource.token)
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            val lat = location.latitude.toString()
                            val lng = location.longitude.toString()

                            Log.i("TESTING", "Success: $lat $lng")

                            val coordinates = "$lat,$lng"

                            // call a function, like in View Model, to do something with location...
                        }
                        else {
                            Log.i("TESTING", "Problem encountered: Location returned null")
                        }
                    }
            }
        }
        else {
            // Run a side-effect (coroutine) to get permission. The permission popup.
            LaunchedEffect(permissionState){
                permissionState.launchPermissionRequest()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloudScout(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    var selectedTab by remember { mutableStateOf("Today") }

    val weather by mainViewModel.weather.collectAsState()
    val locationText = weather?.location?.let {
        "${it.name}, ${it.region} ${it.country}"
    } ?: "Loading..."

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent, darkIcons = false)
        systemUiController.setNavigationBarColor(Color.Transparent, darkIcons = false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgfin),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black.copy(alpha = 0.6f) // semi-transparent background
                            ),
                            shape = RoundedCornerShape(8.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.16.dp)
                        ) {
                            Text(
                                text = locationText,
                                color = Color.White,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
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
                composable("Today") { CurrentWeather(mainViewModel) }
                composable("daily") { DailyForecast(mainViewModel) }
            }
        }
    }
}
