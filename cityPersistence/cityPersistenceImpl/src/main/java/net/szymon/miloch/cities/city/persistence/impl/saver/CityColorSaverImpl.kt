package net.szymon.miloch.cities.city.persistence.impl.saver

import net.szymon.miloch.cities.city.persistence.CityColorSaver
import net.szymon.miloch.cities.city.persistence.impl.dao.CityColorDao
import net.szymon.miloch.cities.city.persistence.impl.entity.CityColorEntity
import net.szymon.miloch.netguru.cities.city.color.CityColor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityColorSaverImpl @Inject constructor(
    private val cityColorDao: CityColorDao
) : CityColorSaver {
    override fun saveCityColor(cityColor: CityColor) {
        cityColorDao.insertCityColor(
            CityColorEntity(
                city = cityColor.city,
                color = cityColor.color,
                emissionDate = cityColor.emissionDate
            )
        )
    }
}