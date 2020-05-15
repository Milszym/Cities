package net.szymon.miloch.cities.city.persistence.impl.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.szymon.miloch.cities.city.persistence.CityColorProvider
import net.szymon.miloch.cities.city.persistence.CityColorSaver
import net.szymon.miloch.cities.city.persistence.impl.CityColorDatabase
import net.szymon.miloch.cities.city.persistence.impl.CityColorDatabase.Companion.CITY_COLOR_DATABASE_NAME
import net.szymon.miloch.cities.city.persistence.impl.dao.CityColorDao
import net.szymon.miloch.cities.city.persistence.impl.provider.CityColorProviderImpl
import net.szymon.miloch.cities.city.persistence.impl.saver.CityColorSaverImpl
import javax.inject.Singleton

@Module
class CityColorPersistenceModule {
    @Provides
    @Singleton
    fun providesCatalogDatabase(context: Context): CityColorDatabase {
        return Room.databaseBuilder(
            context,
            CityColorDatabase::class.java,
            CITY_COLOR_DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesProductDao(catalogDatabase: CityColorDatabase): CityColorDao {
        return catalogDatabase.cityColorDao()
    }

    @Provides
    @Singleton
    fun providesCityColorProvider(cityColorProviderImpl: CityColorProviderImpl): CityColorProvider =
        cityColorProviderImpl

    @Provides
    @Singleton
    fun providesCityColorSaver(cityColorSaverImpl: CityColorSaverImpl): CityColorSaver =
        cityColorSaverImpl
}