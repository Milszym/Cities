package net.szymon.miloch.netguru.cities.city.producer.impl.applifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import net.szymon.miloch.netguru.cities.city.producer.CityProducerController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityProducerAppLifecycleObserver @Inject constructor(
    private val cityProducerController: CityProducerController
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
        cityProducerController.startProducing()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        cityProducerController.stopProducing()
    }
}