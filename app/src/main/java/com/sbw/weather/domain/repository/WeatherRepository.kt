package com.sbw.weather.domain.repository

import com.sbw.weather.domain.util.Resource
import com.sbw.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherInfo(latitude: Double, longitude: Double): Resource<WeatherInfo>
}