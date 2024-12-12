package com.sbw.weather.data.mappers

import com.sbw.weather.data.remote.WeatherDataDto
import com.sbw.weather.data.remote.WeatherDto
import com.sbw.weather.domain.weather.WeatherData
import com.sbw.weather.domain.weather.WeatherInfo
import com.sbw.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {

    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            weatherType = WeatherType.fromWeatherModeObsever(weatherCode)
            )
        )
    }.groupBy {
        it.index/24
    }.mapValues {
        it.value.map { it.data }
    }.also {
        println("${it.keys} : ${it.values}")
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val weatherDataNow = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataForDay = weatherDataMap,
        weatherDataNow = weatherDataNow
    )
}