package net.szymon.miloch.cities.city.persistence.impl.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun dateToString(value: Date?): String? = value.toString()

    @TypeConverter
    fun stringToDate(dateString: String?): Date? = Date(dateString)
}