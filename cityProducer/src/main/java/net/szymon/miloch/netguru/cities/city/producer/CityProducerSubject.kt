package net.szymon.miloch.netguru.cities.city.producer

import io.reactivex.Observable
import net.szymon.miloch.netguru.cities.city.color.CityColor

interface CityProducerSubject {
    val cityColor: Observable<CityColor>
}