package net.szymon.miloch.netguru.cities.city.color.generator

import android.graphics.Color

object CityColorGenerator {
    val cities = listOf(
        "Gdańsk",
        "Warszawa",
        "Poznań",
        "Białystok",
        "Wrocław",
        "Katowice",
        "Kraków"
    )

    val colors = listOf(
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.RED,
        Color.BLACK,
        Color.WHITE
    )

    fun getRandomColor() = colors.random()
    fun getRandomCity() = cities.random()
}