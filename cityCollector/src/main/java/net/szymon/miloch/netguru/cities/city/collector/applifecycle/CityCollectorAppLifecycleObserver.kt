package net.szymon.miloch.netguru.cities.city.collector.applifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import net.szymon.miloch.netguru.cities.city.collector.CityColorCollector
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityCollectorAppLifecycleObserver @Inject constructor(
    private val cityColorCollector: CityColorCollector
) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onEnterForeground() {
        cityColorCollector.startCollecting()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onEnterBackground() {
        cityColorCollector.stopCollecting()
    }
}