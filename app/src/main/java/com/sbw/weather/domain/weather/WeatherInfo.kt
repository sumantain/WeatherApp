package com.sbw.weather.domain.weather


data class WeatherInfo(
    val weatherDataForDay: Map<Int, List<WeatherData>>,
    val weatherDataNow: WeatherData?
)
