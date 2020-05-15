package net.szymon.miloch.netguru.cities.masterdetails.di

import dagger.Module
import net.szymon.miloch.netguru.cities.masterdetails.CityMasterDetailsActivity
import net.szymon.miloch.netguru.cities.masterdetails.details.CityDetailsFragment
import net.szymon.miloch.netguru.cities.masterdetails.list.CityListFragment

@Module(
    includes = [
        CityMasterDetailsActivity.InjectorModule::class,
        CityDetailsFragment.InjectorModule::class,
        CityListFragment.InjectorModule::class
    ]
)
class MasterDetailsAndroidInjectorsModule