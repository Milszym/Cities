package net.szymon.miloch.netguru.cities.city.collector

import io.reactivex.disposables.Disposable
import net.szymon.miloch.cities.city.persistence.CityColorSaver
import net.szymon.miloch.netguru.cities.city.producer.CityProducerSubject
import net.szymon.miloch.netguru.cities.logger.Logger
import net.szymon.miloch.netguru.cities.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityColorCollector @Inject constructor(
    private val cityProducerSubject: CityProducerSubject,
    private val cityColorSaver: CityColorSaver
) {
    private var collectorDisposable: Disposable? = null
    private val logger = Logger(CityColorCollector::class.java.name)

    fun startCollecting() {
        collectorDisposable?.dispose()
        collectorDisposable = cityProducerSubject.cityColor
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                cityColorSaver.saveCityColor(it)
            }, {
                logger.e("Collecting failed", it)
            })
    }

    fun stopCollecting() {
        collectorDisposable?.dispose()
    }
}