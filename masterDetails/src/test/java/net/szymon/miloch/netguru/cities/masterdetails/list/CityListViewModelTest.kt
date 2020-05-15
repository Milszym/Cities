package net.szymon.miloch.netguru.cities.masterdetails.list

import android.graphics.Color
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.mockk.mockk
import net.szymon.miloch.cities.city.persistence.CityColorProvider
import net.szymon.miloch.netguru.cities.city.color.CityColor
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.*

class CityListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val _cityColors: MutableLiveData<List<CityColor>> = MutableLiveData()
    val cityColors: LiveData<List<CityColor>> = _cityColors

    private val cityColorProvider = object : CityColorProvider {
        override fun getCityColorLiveData(): LiveData<List<CityColor>> = cityColors
    }

    private val vm = CityListViewModel(
        cityColorProvider = cityColorProvider
    )

    @Test
    fun `City list live data is transformed into sorted list by city name`() {
        val testObserver = mockk<Observer<List<CityColor>>>(relaxed = true)
        val firstCityName = "Białystok"
        val secondCityName = "Gdańsk"
        val thirdCityName = "Warszawa"
        vm.cityColors.observeForever(testObserver)
        _cityColors.postValue(
            listOf(
                CityColor(
                    city = thirdCityName,
                    color = Color.RED,
                    emissionDate = Date()
                ),
                CityColor(
                    city = firstCityName,
                    color = Color.RED,
                    emissionDate = Date()
                ),
                CityColor(
                    city = secondCityName,
                    color = Color.RED,
                    emissionDate = Date()
                )
            )
        )
        val emittedCityColors = vm.cityColors.value
        assertEquals(emittedCityColors!![0].city, firstCityName)
        assertEquals(emittedCityColors[1].city, secondCityName)
        assertEquals(emittedCityColors[2].city, thirdCityName)
    }
}