package com.example.cloudscout.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.cloudscout.viewmodel.DailyForecast
import com.example.cloudscout.viewmodel.MainViewModel

@Composable
fun DailyForecast(navController: NavController, viewModel: MainViewModel = viewModel()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF87CEEB), Color(0xFFB0E0E6))))
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("3-Day Forecast", fontSize = 28.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(viewModel.forecast) { day: DailyForecast ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.85f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = day.icon),
                                contentDescription = "Forecast Icon",
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(day.date, fontSize = 20.sp)
                                Text("${day.tempHigh} / ${day.tempLow}", fontSize = 16.sp)
                                Text(day.condition, fontSize = 16.sp)
                                Text("Precipitation: ${day.precipitation}", fontSize = 14.sp)
                                Text("Wind: ${day.wind}", fontSize = 14.sp)
                                Text("Humidity: ${day.humidity}", fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Back")
            }
        }
    }
}
