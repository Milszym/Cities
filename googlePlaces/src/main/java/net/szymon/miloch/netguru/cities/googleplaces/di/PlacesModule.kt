package net.szymon.miloch.netguru.cities.googleplaces.di

import android.content.Context
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import net.szymon.miloch.netguru.cities.buildconfig.BuildConfigName
import javax.inject.Named
import javax.inject.Singleton

@Module
class PlacesModule {
    @Provides
    @Singleton
    fun providesPlacesClient(
        context: Context,
        @Named(BuildConfigName.GOOGLE_API_KEY)
        googleApiKey: String
    ): PlacesClient {
        Places.initialize(context, googleApiKey)
        return Places.createClient(context)
    }
}