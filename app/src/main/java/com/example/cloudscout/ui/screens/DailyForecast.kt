package com.example.cloudscout.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cloudscout.viewmodel.MainViewModel


@Composable
fun DailyForecast(navController: NavHostController, viewModel: MainViewModel) {
    val forecasts by viewModel.forecastList.collectAsState()

    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(forecasts) { forecast ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Transparent)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(forecast.date, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text("${forecast.high}°C High  ${forecast.low}°C Low", fontSize = 14.sp)
                            Text(forecast.description, fontSize = 12.sp)
                        }
                        Image(
                            painter = painterResource(id = forecast.icon),
                            contentDescription = "Forecast Icon",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
