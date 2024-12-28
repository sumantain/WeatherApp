package com.sbw.weather.presentation.viewmodel

import android.location.Location
import com.sbw.weather.domain.location.LocationTracker
import com.sbw.weather.domain.repository.WeatherRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class WeatherViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @MockK
    private lateinit var repository: WeatherRepository

    @MockK
    private lateinit var locationTracker: LocationTracker

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        MockKAnnotations.init(this)
        viewModel = WeatherViewModel(repository, locationTracker)
    }

    @Test
    fun `loadWeatherInfo() - success`() = runTest {
        // Mock location and repository response
        val location = Location("").apply {
            latitude = 37.7749
            longitude = -122.4194
        }
        coEvery { locationTracker.getCurrentLocation() } returns location
        coEvery { repository.getWeatherInfo(any(), any()) } returns Result.success(mockk())

        // Call loadWeatherInfo()
        viewModel.loadWeatherInfo()

        // Assertions
        Assert.assertEquals(true, viewModel.state.isLoading)
        Assert.assertEquals(null, viewModel.state.error)
    }

    @Test
    fun `loadWeatherInfo() - error`() = runTest {
        // Mock location and repository response
        val location = Location("").apply {
            latitude = 37.7749
            longitude = -122.4194
        }
        coEvery { locationTracker.getCurrentLocation() } returns location

        // Call loadWeatherInfo()
        viewModel.loadWeatherInfo()

        // Assertions
        Assert.assertEquals(false, viewModel.state.isLoading)
    }

}