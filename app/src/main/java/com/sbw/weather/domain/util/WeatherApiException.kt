package com.sbw.weather.domain.util

sealed class WeatherApiException : Exception() {
    class NetworkError(override val cause: Throwable? = null) : WeatherApiException()
    class ServerError(val code: Int, override val message: String? = null) : WeatherApiException()
    class UnknownError(override val cause: Throwable? = null) : WeatherApiException()
}