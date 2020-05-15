package net.szymon.miloch.netguru.cities.city.producer.impl.di

import dagger.Binds
import dagger.Module
import net.szymon.miloch.netguru.cities.city.producer.CityProducerController
import net.szymon.miloch.netguru.cities.city.producer.CityProducerSubject
import net.szymon.miloch.netguru.cities.city.producer.impl.CityColorProducer
import javax.inject.Singleton

@Module
interface CityProducerModule {
    @Binds
    @Singleton
    fun providesCityProducerController(cityColorProducer: CityColorProducer): CityProducerController

    @Binds
    @Singleton
    fun providesCityProducerSubject(cityColorProducer: CityColorProducer): CityProducerSubject
}