package net.szymon.miloch.netguru.cities.city.producer.impl;

import net.szymon.miloch.netguru.cities.city.color.CityColor;
import net.szymon.miloch.netguru.cities.city.color.generator.CityColorGenerator;
import net.szymon.miloch.netguru.cities.city.producer.CityProducerController;
import net.szymon.miloch.netguru.cities.city.producer.CityProducerSubject;
import net.szymon.miloch.netguru.cities.logger.Logger;
import net.szymon.miloch.netguru.cities.schedulers.Schedulers;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

@Singleton
public class CityColorProducer implements CityProducerController, CityProducerSubject {

    final static Long PRODUCER_INTERVAL_MILLIS = 5000L;

    private Disposable producerDisposable;
    private final PublishSubject<CityColor> _cityColor = PublishSubject.create();

    private final Observable<CityColor> cityColor = _cityColor;

    private final Logger logger = new Logger(CityColorProducer.class.getName());

    @Inject
    CityColorProducer() {
    }

    @Override
    @NotNull
    public Observable<CityColor> getCityColor() {
        return cityColor;
    }

    @Override
    public void startProducing() {
        logger.d("Start producing city colors.");
        if (producerDisposable != null) {
            producerDisposable.dispose();
        }
        producerDisposable = Observable.interval(PRODUCER_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
                .timeInterval()
                .observeOn(Schedulers.INSTANCE.io())
                .subscribeOn(Schedulers.INSTANCE.io())
                .subscribe(aLong -> emitRandomCityColor(),
                        throwable -> logger.e("Producing failed", throwable)
                );
    }

    private void emitRandomCityColor() {
        int color = CityColorGenerator.INSTANCE.getRandomColor();
        String city = CityColorGenerator.INSTANCE.getRandomCity();
        CityColor cityColor = new CityColor(
                city,
                color,
                new Date()
        );
        _cityColor.onNext(cityColor);
        logger.d(String.format("Emitted new city color: %s", cityColor));
    }

    @Override
    public void stopProducing() {
        logger.d("Stop producing city colors.");
        if (producerDisposable != null) {
            producerDisposable.dispose();
        }
    }
}
