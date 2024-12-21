package com.sbw.weather.data.repository

import com.sbw.weather.data.mappers.toWeatherInfo
import com.sbw.weather.data.remote.WeatherApi
import com.sbw.weather.domain.repository.WeatherRepository
import com.sbw.weather.domain.util.WeatherApiException
import com.sbw.weather.domain.weather.WeatherInfo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * A repository implementation that uses the {@link WeatherApi} to fetch weather data.
 *
 * <p>This class provides a layer of abstraction between the data source ({@link WeatherApi})
 * and the rest of the application. It handles the communication with the API and
 * returns the weather information as a {@link WeatherInfo} object.</p>
 *
 * @see WeatherApi
 * @see WeatherInfo
 * @see Result
 * @since 1.0
 */
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    /**
     * Retrieves weather information for the specified latitude and longitude.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return A {@link Result} object containing either the weather information or an error.
     */
    override suspend fun getWeatherInfo(
        latitude: Double,
        longitude: Double
    ): Result<WeatherInfo> {
        return try {
            val weatherInfo = api.getWeatherInfo(latitude, longitude).toWeatherInfo()
            Result.success(weatherInfo)
        } catch (e: IOException) {
            Result.failure(WeatherApiException.NetworkError(e))
        } catch (e: HttpException) {
            val code = e.code()
            val message = e.response()?.errorBody()?.string()
            Result.failure(WeatherApiException.ServerError(code, message))
        } catch (e: Exception) {
            Result.failure(WeatherApiException.UnknownError(e))
        }
    }
}