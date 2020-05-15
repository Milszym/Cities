package net.szymon.miloch.cities.city.persistence.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.szymon.miloch.cities.city.persistence.impl.converter.DateConverter
import net.szymon.miloch.cities.city.persistence.impl.dao.CityColorDao
import net.szymon.miloch.cities.city.persistence.impl.entity.CityColorEntity

@Database(
    entities = [
        CityColorEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [DateConverter::class])
abstract class CityColorDatabase : RoomDatabase() {
    abstract fun cityColorDao(): CityColorDao

    companion object {
        const val CITY_COLOR_DATABASE_NAME = "CityColorDatabase.sqlite"
    }
}