package com.sbw.weather

import android.content.Context
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class WeatherAppTest {

    private lateinit var weatherApp: WeatherApp


    @Test
    fun testOnCreate() {
        weatherApp.onCreate()

        Assert.assertNotNull(weatherApp.assets)
        verify { weatherApp.applicationContext }
    }
}