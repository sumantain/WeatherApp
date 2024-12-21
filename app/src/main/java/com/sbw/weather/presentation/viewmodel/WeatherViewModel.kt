package com.sbw.weather.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbw.weather.domain.location.LocationTracker
import com.sbw.weather.domain.repository.WeatherRepository
import com.sbw.weather.domain.util.WeatherApiException
import com.sbw.weather.presentation.state.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.Result
import kotlin.text.format

@HiltViewModel
public class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                val result = repository.getWeatherInfo(location.latitude, location.longitude)
                if(result.isSuccess){
                    state = state.copy(
                            weatherInfo = result.getOrNull(),
                            isLoading = false,
                            error = null
                        )
                } else {
                    val exception = result.exceptionOrNull()
                    when (exception) {
                        is WeatherApiException.NetworkError -> {
                            // ... handle network error ...
                            state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = exception.message
                        )
                        }
                        is WeatherApiException.ServerError -> {
                            // ... handle server error ...
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = exception.message
                            )
                        }
                        is WeatherApiException.UnknownError -> {
                            // ... handle unknown error ...
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = exception.message
                            )
                        }
                        else -> {
                            // ... handle other errors ...
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = exception?.message
                            )
                        }
                    }
                }
            }?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }
        }
    }

    fun formatTime(time: LocalDateTime): String {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}