package net.szymon.miloch.netguru.cities.masterdetails.details

import android.graphics.Color
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.android.gms.maps.model.LatLng
import io.mockk.every
import io.mockk.mockk
import io.reactivex.observers.TestObserver
import net.szymon.miloch.netguru.cities.city.color.CityColor
import net.szymon.miloch.netguru.cities.city.locator.CityLocator
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

class CityDetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val cityLocator = mockk<CityLocator>(relaxed = true)

    private val vm = CityDetailsViewModel(
        cityLocator = cityLocator
    )

    @Test
    fun `City locator after successfully locating the city produces lat and lng for this city`() {
        val cityName = "Gdańsk"
        val cityLatLng = LatLng(54.351900, 18.644771)
        every { cityLocator.locateByCityName(cityName, any()) } answers {
            val onLocated = it.invocation.args[1] as (Result<LatLng>) -> Unit
            onLocated(Result.success(cityLatLng))
        }
        val testObserver = mockk<Observer<LatLng>>(relaxed = true)
        vm.cityLatLng.observeForever(testObserver)
        val errorObserver = TestObserver.create<Int>().apply {
            vm.error.subscribe(this)
        }

        vm.setCityColor(
            CityColor(
                city = cityName,
                color = Color.RED,
                emissionDate = Date()
            )
        )
        assertEquals(vm.cityLatLng.value, cityLatLng)
        errorObserver.assertValueCount(0)
    }

    @Test
    fun `City locator after locating city failure produces error`() {
        val cityName = "NotExistingGdańsk"
        every { cityLocator.locateByCityName(cityName, any()) } answers {
            val onLocated = it.invocation.args[1] as (Result<LatLng>) -> Unit
            onLocated(Result.failure(CityLocator.LocationException(cityName)))
        }

        val errorObserver = TestObserver.create<Int>().apply {
            vm.error.subscribe(this)
        }

        vm.setCityColor(
            CityColor(
                city = cityName,
                color = Color.RED,
                emissionDate = Date()
            )
        )
        errorObserver.assertValueCount(1)
    }
}