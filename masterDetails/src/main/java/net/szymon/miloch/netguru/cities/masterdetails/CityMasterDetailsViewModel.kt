package net.szymon.miloch.netguru.cities.masterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.szymon.miloch.netguru.cities.city.color.CityColor
import javax.inject.Inject

class CityMasterDetailsViewModel @Inject constructor() : ViewModel() {

    private val _cityColorDetails: MutableLiveData<CityColor> = MutableLiveData()
    val cityColorDetails: LiveData<CityColor> = _cityColorDetails

    fun setItem(cityColor: CityColor) {
        _cityColorDetails.postValue(cityColor)
    }

    fun clearItem() {
        _cityColorDetails.value = null
    }
}
