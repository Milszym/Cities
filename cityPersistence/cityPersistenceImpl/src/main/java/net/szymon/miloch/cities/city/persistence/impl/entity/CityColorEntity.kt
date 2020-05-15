package net.szymon.miloch.cities.city.persistence.impl.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.szymon.miloch.cities.city.persistence.impl.entity.CityColorEntity.Companion.CITY_COLOR_TABLE
import java.util.*

@Entity(
    tableName = CITY_COLOR_TABLE
)
data class CityColorEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val city: String,
    val color: Int,
    val emissionDate: Date
) {
    companion object {
        const val CITY_COLOR_TABLE = "city_color"
    }
}