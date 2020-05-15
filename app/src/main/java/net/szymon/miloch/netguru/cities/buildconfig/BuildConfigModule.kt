package net.szymon.miloch.netguru.cities.buildconfig

import dagger.Module
import dagger.Provides
import net.szymon.miloch.netguru.cities.BuildConfig
import javax.inject.Named

@Module
class BuildConfigModule {
    @Provides
    @Named(BuildConfigName.GOOGLE_API_KEY)
    fun providesGoogleApiKey(): String = BuildConfig.GOOGLE_API_KEY
}