package net.szymon.miloch.cities.city.persistence.impl.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import net.szymon.miloch.cities.city.persistence.CityColorProvider
import net.szymon.miloch.cities.city.persistence.impl.dao.CityColorDao
import net.szymon.miloch.netguru.cities.city.color.CityColor
import javax.inject.Inject

class CityColorProviderImpl @Inject constructor(
    private val cityColorDao: CityColorDao
) : CityColorProvider {
    override fun getCityColorLiveData(): LiveData<List<CityColor>> {
        return Transformations.map(cityColorDao.getCityColorLiveData()) {
            it.map { entity ->
                CityColor(
                    city = entity.city,
                    color = entity.color,
                    emissionDate = entity.emissionDate
                )
            }
        }
    }
}