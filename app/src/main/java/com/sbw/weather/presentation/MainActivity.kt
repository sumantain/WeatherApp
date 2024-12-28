package com.sbw.weather.presentation

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sbw.weather.R
import com.sbw.weather.presentation.screens.WeatherCard
import com.sbw.weather.presentation.screens.WeatherForecast
import com.sbw.weather.presentation.ui.theme.WeatherTheme
import com.sbw.weather.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: WeatherViewModel by viewModels<WeatherViewModel>()
    lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher= registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){ permission ->
            if (permission[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                viewModel.loadWeatherInfo()
            } else {
                // Check if rationale should be shown
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                    shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    initiatesPermissionRequest()

                } else {
                    // User has denied permissions and selected "Don't ask again"
                    // Handle this case gracefully (e.g., disable location-based features)
                    Toast.makeText(this,
                        getString(R.string.location_permission_is_permanently_denied_you_can_enable_it_in_the_app_settings), Toast.LENGTH_LONG).show()
                }
            }
        }

        //This method initiates the permission request
        initiatesPermissionRequest()

        setContent {
            viewModel.state.let { loadState ->
                WeatherTheme {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.LightGray)
                        ) {
                            WeatherCard(
                                state = loadState,
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            WeatherForecast(state = loadState)

                        }

                        if(loadState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        loadState.error?.let { error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun initiatesPermissionRequest(){
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }
}

