package net.szymon.miloch.cities.city.persistence

import net.szymon.miloch.netguru.cities.city.color.CityColor

interface CityColorSaver {
    fun saveCityColor(cityColor: CityColor)
}