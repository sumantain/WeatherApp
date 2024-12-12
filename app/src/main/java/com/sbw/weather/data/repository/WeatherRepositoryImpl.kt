package com.sbw.weather.data.repository

import com.sbw.weather.data.mappers.toWeatherInfo
import com.sbw.weather.data.remote.WeatherApi
import com.sbw.weather.domain.repository.WeatherRepository
import com.sbw.weather.domain.util.Resource
import com.sbw.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherInfo(
                    latitude = latitude,
                    longitude = longitude
                ).toWeatherInfo()
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred.")
        }
    }
}