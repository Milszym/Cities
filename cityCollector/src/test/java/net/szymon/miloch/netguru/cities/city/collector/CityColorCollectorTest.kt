package net.szymon.miloch.netguru.cities.city.collector

import android.graphics.Color
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subjects.PublishSubject
import net.szymon.miloch.cities.city.persistence.CityColorSaver
import net.szymon.miloch.netguru.cities.city.color.CityColor
import net.szymon.miloch.netguru.cities.city.producer.CityProducerSubject
import net.szymon.miloch.netguru.cities.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.*

class CityColorCollectorTest {

    private val subject: PublishSubject<CityColor> = PublishSubject.create()

    private val cityProducerSubject = object : CityProducerSubject {
        override val cityColor: Observable<CityColor>
            get() = subject
    }

    private val cityColorSaver = mockk<CityColorSaver>(relaxed = true)

    private val cityColorCollector = CityColorCollector(
        cityProducerSubject = cityProducerSubject,
        cityColorSaver = cityColorSaver
    )

    private val testScheduler = TestScheduler()

    @Before
    fun before() {
        mockkObject(Schedulers)
        every { Schedulers.io() } returns testScheduler
    }

    @Test
    fun `Collector should collect emitted value`() {
        cityColorCollector.startCollecting()
        val cityColor = CityColor(
            city = "Gdańsk",
            color = Color.RED,
            emissionDate = Date()
        )
        testScheduler.triggerActions()
        subject.onNext(cityColor)
        testScheduler.triggerActions()
        verify(exactly = 1) { cityColorSaver.saveCityColor(any()) }
        cityColorCollector.stopCollecting()
    }

    @Test
    fun `Collector should collect emitted values`() {
        cityColorCollector.startCollecting()
        val cityColor = CityColor(
            city = "Gdańsk",
            color = Color.RED,
            emissionDate = Date()
        )
        testScheduler.triggerActions()
        subject.onNext(cityColor)
        subject.onNext(cityColor.copy(city = "Białystok"))
        testScheduler.triggerActions()
        verify(exactly = 2) { cityColorSaver.saveCityColor(any()) }
        cityColorCollector.stopCollecting()
    }

    @Test
    fun `Collector should not collect emitted values if stop was called`() {
        cityColorCollector.startCollecting()
        val cityColor = CityColor(
            city = "Gdańsk",
            color = Color.RED,
            emissionDate = Date()
        )
        cityColorCollector.stopCollecting()
        testScheduler.triggerActions()
        subject.onNext(cityColor)
        testScheduler.triggerActions()
        verify(exactly = 0) { cityColorSaver.saveCityColor(any()) }
        cityColorCollector.stopCollecting()
    }

    @Test
    fun `Collector should not collect emitted values if start was not called`() {
        val cityColor = CityColor(
            city = "Gdańsk",
            color = Color.RED,
            emissionDate = Date()
        )
        testScheduler.triggerActions()
        subject.onNext(cityColor)
        testScheduler.triggerActions()
        verify(exactly = 0) { cityColorSaver.saveCityColor(any()) }
        cityColorCollector.stopCollecting()
    }

    @Test
    fun `Collector should not collect emitted values after stop was called`() {
        cityColorCollector.startCollecting()
        val cityColor = CityColor(
            city = "Gdańsk",
            color = Color.RED,
            emissionDate = Date()
        )
        testScheduler.triggerActions()
        subject.onNext(cityColor)
        testScheduler.triggerActions()
        cityColorCollector.stopCollecting()
        subject.onNext(cityColor)
        testScheduler.triggerActions()
        verify(exactly = 1) { cityColorSaver.saveCityColor(any()) }
    }
}