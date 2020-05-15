package net.szymon.miloch.cities.city.persistence.impl.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.szymon.miloch.cities.city.persistence.impl.entity.CityColorEntity
import net.szymon.miloch.cities.city.persistence.impl.entity.CityColorEntity.Companion.CITY_COLOR_TABLE

@Dao
interface CityColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityColor(cityColorEntity: CityColorEntity)

    @Query("SELECT * FROM $CITY_COLOR_TABLE")
    fun getCityColorLiveData(): LiveData<List<CityColorEntity>>
}