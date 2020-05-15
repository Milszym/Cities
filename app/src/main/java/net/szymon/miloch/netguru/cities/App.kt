package net.szymon.miloch.netguru.cities

import android.app.Activity
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import net.szymon.miloch.netguru.cities.city.collector.applifecycle.CityCollectorAppLifecycleObserver
import net.szymon.miloch.netguru.cities.city.producer.impl.applifecycle.CityProducerAppLifecycleObserver
import net.szymon.miloch.netguru.cities.di.DaggerAppComponent
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var cityProducerAppLifecycleObserver: CityProducerAppLifecycleObserver

    @Inject
    lateinit var cityCollectorAppLifecycleObserver: CityCollectorAppLifecycleObserver

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(cityProducerAppLifecycleObserver)
        ProcessLifecycleOwner.get().lifecycle.addObserver(cityCollectorAppLifecycleObserver)
    }

}