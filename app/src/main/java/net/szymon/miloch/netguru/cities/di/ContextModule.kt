package net.szymon.miloch.netguru.cities.di

import android.content.Context
import dagger.Binds
import dagger.Module
import net.szymon.miloch.netguru.cities.App

@Module
interface ContextModule {
    @Binds
    fun bindContext(app: App): Context
}