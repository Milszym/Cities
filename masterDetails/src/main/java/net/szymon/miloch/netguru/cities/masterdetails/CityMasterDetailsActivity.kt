package net.szymon.miloch.netguru.cities.masterdetails

import android.os.Bundle
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.android.synthetic.main.master_details_activity.*
import kotlinx.android.synthetic.main.master_details_toolbar.*
import net.szymon.miloch.netguru.cities.base.BaseActivity
import net.szymon.miloch.netguru.cities.base.ViewModelFactory
import net.szymon.miloch.netguru.cities.masterdetails.details.CityDetailsFragment
import net.szymon.miloch.netguru.cities.masterdetails.list.CityListFragment
import javax.inject.Inject

class CityMasterDetailsActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory<CityMasterDetailsViewModel>
    private lateinit var vm: CityMasterDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.master_details_activity)
        vm = vmFactory.get()

        initializeMainFragment()
        initializeToolbar()

        vm.cityColorDetails.observe {
            setCityColorDetails()
        }
    }

    private fun initializeMainFragment() {
        val fragmentClass = if (cityDetailsContainer == null && vm.cityColorDetails.value != null) {
            CityDetailsFragment::class
        } else {
            CityListFragment::class
        }.java
        supportFragmentManager.beginTransaction()
            .replace(cityList.id, fragmentClass, null)
            .commit()
    }

    private fun initializeToolbar() {
        val currentCityColor = vm.cityColorDetails.value
        if (currentCityColor != null) {
            toolbar.setBackgroundColor(currentCityColor.color)
            toolbar.title = currentCityColor.city
        } else {
            toolbar.setTitle(R.string.master_details_city_placeholder)
        }
    }

    private fun setCityColorDetails() {
        if (cityDetailsContainer == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(cityList.id, CityDetailsFragment::class.java, null)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (cityDetailsContainer == null) {
            vm.clearItem()
            initializeMainFragment()
        }
    }

    @Module
    interface InjectorModule {
        @ContributesAndroidInjector
        fun contributesCityMasterDetailsFragment(): CityMasterDetailsActivity
    }
}