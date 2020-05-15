package net.szymon.miloch.netguru.cities

import androidx.lifecycle.ViewModel
import net.szymon.miloch.netguru.cities.city.producer.CityProducerSubject
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    cityProducerSubject: CityProducerSubject
) : ViewModel() {
    val cityColor = cityProducerSubject.cityColor
}
