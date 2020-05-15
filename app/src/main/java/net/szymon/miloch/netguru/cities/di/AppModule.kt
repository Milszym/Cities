package net.szymon.miloch.netguru.cities.di

import dagger.Module
import net.szymon.miloch.cities.city.persistence.impl.di.CityColorPersistenceModule
import net.szymon.miloch.netguru.cities.buildconfig.BuildConfigModule
import net.szymon.miloch.netguru.cities.city.producer.impl.di.CityProducerModule
import net.szymon.miloch.netguru.cities.googleplaces.di.PlacesModule

@Module(
    includes = [
        ContextModule::class,
        BuildConfigModule::class,
        PlacesModule::class,
        CityProducerModule::class,
        CityColorPersistenceModule::class
    ]
)
class AppModule