package net.szymon.miloch.netguru.cities.city.color

import java.io.Serializable
import java.util.*

data class CityColor(
    val city: String,
    val color: Int,
    val emissionDate: Date
) : Serializable