package net.szymon.miloch.netguru.cities.city.color.generator

import org.junit.Assert.assertTrue
import org.junit.Test

class CityColorGeneratorTest {
    @Test
    fun `Generator should return single city from defined cities`() {
        val city = CityColorGenerator.getRandomCity()
        assertTrue(CityColorGenerator.cities.contains(city))
    }

    @Test
    fun `Generator should return single color from defined colors`() {
        val color = CityColorGenerator.getRandomColor()
        assertTrue(CityColorGenerator.colors.contains(color))
    }
}