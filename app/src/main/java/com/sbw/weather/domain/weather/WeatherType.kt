package com.sbw.weather.domain.weather

import androidx.annotation.DrawableRes
import com.sbw.weather.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val icon: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        icon = R.drawable.ic_sunny
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        icon = R.drawable.ic_cloudy
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        icon = R.drawable.ic_cloudy
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        icon = R.drawable.ic_cloudy
    )
    object Smoky: WeatherType(
        weatherDesc = "Smokey",
        icon = R.drawable.ic_smoke
    )
    object Haze: WeatherType(
        weatherDesc = "Haze",
        icon = R.drawable.ic_haze
    )
    object DustInAir: WeatherType(
        weatherDesc = "Dust In Air",
        icon = R.drawable.ic_dust
    )
    object SandInWind: WeatherType(
        weatherDesc = "Sand In Wind",
        icon = R.drawable.ic_dust
    )
    object DustOrSandWhirl: WeatherType(
        weatherDesc = "Dust Or Sand Whirl",
        icon = R.drawable.ic_dust
    )
    object DustOrSandStorm: WeatherType(
        weatherDesc = "Dust Or Sand Storm",
        icon = R.drawable.ic_dust
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        icon = R.drawable.ic_very_cloudy
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        icon = R.drawable.ic_very_cloudy
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        icon = R.drawable.ic_rainshower
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        icon = R.drawable.ic_rainshower
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        icon = R.drawable.ic_rainshower
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        icon = R.drawable.ic_snowyrainy
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        icon = R.drawable.ic_snowyrainy
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        icon = R.drawable.ic_rainy
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        icon = R.drawable.ic_rainy
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        icon = R.drawable.ic_rainy
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        icon = R.drawable.ic_snowyrainy
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        icon = R.drawable.ic_snowy
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        icon = R.drawable.ic_heavysnow
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        icon = R.drawable.ic_heavysnow
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        icon = R.drawable.ic_heavysnow
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        icon = R.drawable.ic_rainshower
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        icon = R.drawable.ic_rainshower
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        icon = R.drawable.ic_rainshower
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        icon = R.drawable.ic_snowy
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        icon = R.drawable.ic_snowy
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        icon = R.drawable.ic_thunder
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        icon = R.drawable.ic_rainythunder
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        icon = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWeatherModeObsever(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                4 -> Smoky
                5 -> Haze
                6 -> DustInAir
                7 -> SandInWind
                8 -> DustOrSandWhirl
                9 -> DustOrSandStorm
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}