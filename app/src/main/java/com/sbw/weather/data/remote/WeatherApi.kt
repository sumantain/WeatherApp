package com.sbw.weather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit API interface for fetching weather data.
 *
 * <p>This interface defines methods for retrieving weather information
 * based on latitude and longitude coordinates.</p>
 *
 * <h3>Example Usage:</h3>
 * <pre>
 * {@code
 * WeatherApi weatherApi = RetrofitClient.create(WeatherApi.class);
 * Call<WeatherInfo> call = weatherApi.getWeatherInfo(37.7749, -122.4194);
 * call.enqueue(new Callback<WeatherInfo>() {
 *     @Override
 *     public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
 *         // Handle successful response
 *     }
 *
 *     @Override
 *     public void onFailure(Call<WeatherInfo> call, Throwable t) {
 *         // Handle failure
 *     }
 * });
 * }
 * </pre>
 *
 * @see RetrofitClient
 * @since 1.0
 */

interface WeatherApi {

    /**
     * Retrieves weather information for the specified latitude and longitude.
     *
     * @param latitude  The latitude coordinate.
     * @param longitude The longitude coordinate.
     * @return A {@link Call} object that represents the asynchronous request for weather data.
     */
    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherInfo(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherDto
}