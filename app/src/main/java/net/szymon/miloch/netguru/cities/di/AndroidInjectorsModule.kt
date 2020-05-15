package net.szymon.miloch.netguru.cities.di

import dagger.Module
import net.szymon.miloch.netguru.cities.SplashActivity
import net.szymon.miloch.netguru.cities.masterdetails.di.MasterDetailsAndroidInjectorsModule

@Module(
    includes = [
        SplashActivity.InjectorModule::class,
        MasterDetailsAndroidInjectorsModule::class
    ]
)
class AndroidInjectorsModule