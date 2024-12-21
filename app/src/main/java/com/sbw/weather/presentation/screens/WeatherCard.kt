package com.sbw.weather.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sbw.weather.R
import com.sbw.weather.presentation.state.WeatherState
import com.sbw.weather.presentation.viewmodel.WeatherViewModel
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {

    val viewModel: WeatherViewModel = viewModel()

    state.weatherInfo?.weatherDataNow?.let { data ->

        Card(

            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        R.string.today, viewModel.formatTime(data.time)
                    ),
                    modifier = Modifier.align(Alignment.End),
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = data.weatherType.icon),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.c, data.temperatureCelsius),
                    fontSize = 50.sp,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherCardInfo(
                        value = data.pressure.roundToInt(),
                        unit = stringResource(R.string.hpa),
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                        iconTint = Color.Red,
                        textStyle = TextStyle(color = Color.Red)
                    )

                    WeatherCardInfo(
                        value = data.humidity.roundToInt(),
                        unit = stringResource(R.string.percentage),
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                        iconTint = Color.Red,
                        textStyle = TextStyle(color = Color.Red)
                    )

                    WeatherCardInfo(
                        value = data.windSpeed.roundToInt(),
                        unit = stringResource(R.string.km_h),
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                        iconTint = Color.Red,
                        textStyle = TextStyle(color = Color.Red)
                    )
                }
            }
        }

    }
}