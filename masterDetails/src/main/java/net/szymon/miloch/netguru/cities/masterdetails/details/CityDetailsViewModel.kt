package net.szymon.miloch.netguru.cities.masterdetails.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import net.szymon.miloch.netguru.cities.city.color.CityColor
import net.szymon.miloch.netguru.cities.city.locator.CityLocator
import net.szymon.miloch.netguru.cities.masterdetails.R
import javax.inject.Inject

class CityDetailsViewModel @Inject constructor(
    private val cityLocator: CityLocator
) : ViewModel() {

    private var currentCityColor: CityColor? = null

    private val _cityLatLng: MutableLiveData<LatLng> = MutableLiveData()
    val cityLatLng: LiveData<LatLng> = _cityLatLng

    private val _error: PublishSubject<Int> = PublishSubject.create()
    val error: Observable<Int> = _error

    fun setCityColor(cityColor: CityColor) {
        locateCity(cityColor.city)
    }

    private fun locateCity(city: String) {
        cityLocator.locateByCityName(city) { result ->
            if (result.isSuccess) {
                val latLng = result.getOrNull()
                if (latLng != null) {
                    _cityLatLng.postValue(latLng)
                } else {
                    _error.onNext(R.string.master_details_city_details_city_not_found)
                }
            } else {
                _error.onNext(R.string.master_details_city_details_city_not_found)
            }
        }
    }
}
