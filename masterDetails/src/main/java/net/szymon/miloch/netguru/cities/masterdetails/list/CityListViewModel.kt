package net.szymon.miloch.netguru.cities.masterdetails.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import net.szymon.miloch.cities.city.persistence.CityColorProvider
import net.szymon.miloch.netguru.cities.city.color.CityColor
import javax.inject.Inject

class CityListViewModel @Inject constructor(
    cityColorProvider: CityColorProvider
) : ViewModel() {
    val cityColors: LiveData<List<CityColor>> =
        Transformations.map(cityColorProvider.getCityColorLiveData()) {
            it.sortedBy { cityColor -> cityColor.city }
        }
}
