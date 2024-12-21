import com.sbw.weather.data.remote.WeatherApi
import com.sbw.weather.data.repository.WeatherRepositoryImpl
import com.sbw.weather.domain.weather.WeatherInfo
import io.mockk.MockKStubScope
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class WeatherRepositoryImplTest {

    private val weatherApi = mockk<WeatherApi>()
    private val repository = WeatherRepositoryImpl(weatherApi)

    @Test
    fun testGetWeatherInfo_success() = runBlocking {
        // Arrange
        val latitude = 37.7749
        val longitude = -122.4194
        val expectedWeatherInfo = mockk<WeatherInfo>()
        coEvery { weatherApi.getWeatherInfo(latitude, longitude) } returns expectedWeatherInfo

        // Act
        val result = repository.getWeatherInfo(latitude, longitude)

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(expectedWeatherInfo, result.getOrNull())
    }

    @Test
    fun testGetWeatherInfo_failure() = runBlocking {
        // Arrange
        val latitude = 37.7749
        val longitude = -122.4194
        val exception = Exception("Network error")
        coEvery { weatherApi.getWeatherInfo(latitude, longitude) } throws exception

        // Act
        val result = repository.getWeatherInfo(latitude, longitude)

        // Assert
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

private infix fun <T, B> MockKStubScope<T, B>.returns(expectedWeatherInfo: WeatherInfo) {



}
