package com.sbw.weather.presentation

import android.Manifest
import android.app.Activity
import android.app.ActivityOptions
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.base.Verify.verify
import com.sbw.weather.presentation.viewmodel.WeatherViewModel
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.collections.toTypedArray
import kotlin.collections.mutableMapOf as mutableMapOf

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testPermissionRequest() {
        // Mock the permission result
        val mockPermissionResult = mutableMapOf(
            Manifest.permission.ACCESS_FINE_LOCATION to true,
            Manifest.permission.ACCESS_COARSE_LOCATION to true
        )

        // Grant permissions
        activityScenarioRule.scenario.onActivity {
            it.permissionLauncher.launch(mockPermissionResult.keys.toTypedArray())
        }

        // Verify that loadWeatherInfo() is called
        verify(exactly = 1) { activityScenarioRule.scenario.onActivity { it.viewModel.loadWeatherInfo() } }
    }

}

