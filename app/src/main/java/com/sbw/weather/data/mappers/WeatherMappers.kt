package com.sbw.weather.data.mappers

import com.sbw.weather.data.remote.WeatherDataDto
import com.sbw.weather.data.remote.WeatherDto
import com.sbw.weather.domain.weather.WeatherData
import com.sbw.weather.domain.weather.WeatherInfo
import com.sbw.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * A data class representing weather data with an associated index.
 *
 * @property index The index of the weather data point.
 * @property data The actual weather data.
 */
private data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)

private const val DAY_IN_HOURS = 24

/**
 * Maps a [WeatherDataDto] object to a map of weather data organized by day.
 *
 * @receiver The [WeatherDataDto] object to map.
 * @return A map where the key represents the day (0 for today, 1 for tomorrow, etc.) and the value is a list of [WeatherData] objects for that day.
 */
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
    }.chunked(DAY_IN_HOURS) // Group data into 24-hour chunks (days)
        .mapIndexed { dayIndex, dailyData -> dayIndex to dailyData.map { it.data } } // Map to day index and weather data
        .toMap() // Convert to a map
}

/**
 * Maps a [WeatherDto] object to a [WeatherInfo] object.
 *
 * @receiver The [WeatherDto] object to map.
 * @return A [WeatherInfo] object representing the weather information.
 */
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