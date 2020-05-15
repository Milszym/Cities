package net.szymon.miloch.cities.city.persistence

import androidx.lifecycle.LiveData
import net.szymon.miloch.netguru.cities.city.color.CityColor

interface CityColorProvider {
    fun getCityColorLiveData(): LiveData<List<CityColor>>
}