package net.szymon.miloch.netguru.cities.city.producer.impl

import io.mockk.every
import io.mockk.mockkObject
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import net.szymon.miloch.netguru.cities.city.color.CityColor
import net.szymon.miloch.netguru.cities.city.producer.impl.CityColorProducer.PRODUCER_INTERVAL_MILLIS
import net.szymon.miloch.netguru.cities.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit


class CityColorProducerTest {

    private val cityColorProducer = CityColorProducer()

    private val cityColorObserver = TestObserver.create<CityColor>().apply {
        cityColorProducer.cityColor.subscribe(this)
    }

    private val testScheduler = TestScheduler()

    @Before
    fun before() {
        mockkObject(Schedulers)
        every { Schedulers.io() } returns testScheduler
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @Test
    fun `City color producer does not produce values if not started`() {
        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS * 2, TimeUnit.MILLISECONDS)
        cityColorObserver.assertNoValues()
    }

    @Test
    fun `City color producer produces value after 5 seconds if started`() {
        cityColorProducer.startProducing()

        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(1)

        cityColorProducer.stopProducing()
    }

    @Test
    fun `City color producer produces values, each after 5 seconds if started`() {
        cityColorProducer.startProducing()

        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(1)
        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(2)

        cityColorProducer.stopProducing()
    }

    @Test
    fun `City color producer produces 10 values`() {
        cityColorProducer.startProducing()
        val valuesQuantity = 10
        testScheduler.advanceTimeBy(
            PRODUCER_INTERVAL_MILLIS * valuesQuantity,
            TimeUnit.MILLISECONDS
        )
        cityColorObserver.assertValueCount(valuesQuantity)

        cityColorProducer.stopProducing()
    }

    @Test
    fun `City color producer produces value after 5 seconds, but does not produce if stopped`() {
        cityColorProducer.startProducing()

        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(1)

        cityColorProducer.stopProducing()

        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(1)
    }

    @Test
    fun `City color producer produces values after restart`() {
        cityColorProducer.startProducing()
        cityColorProducer.stopProducing()
        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(0)

        cityColorProducer.startProducing()
        testScheduler.advanceTimeBy(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
        cityColorObserver.assertValueCount(1)
        cityColorProducer.stopProducing()
    }

}