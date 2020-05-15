package net.szymon.miloch.netguru.cities.masterdetails.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.android.synthetic.main.master_details_city_list_fragment.*
import net.szymon.miloch.netguru.cities.base.BaseFragment
import net.szymon.miloch.netguru.cities.base.ViewModelFactory
import net.szymon.miloch.netguru.cities.city.color.CityColor
import net.szymon.miloch.netguru.cities.masterdetails.CityMasterDetailsViewModel
import net.szymon.miloch.netguru.cities.masterdetails.R
import javax.inject.Inject

class CityListFragment : BaseFragment() {

    @Inject
    lateinit var sharedVmFactory: ViewModelFactory<CityMasterDetailsViewModel>
    private lateinit var sharedVm: CityMasterDetailsViewModel

    @Inject
    lateinit var vmFactory: ViewModelFactory<CityListViewModel>
    private lateinit var vm: CityListViewModel

    private val cityColorAdapter = CityListAdapter(listOf(), ::onCityColorClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.master_details_city_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = vmFactory.get()
        sharedVm = sharedVmFactory.getForActivity()
        citiesRecyclerView.adapter = cityColorAdapter

        vm.cityColors.observe {
            cityColorAdapter.updateItems(it)
        }
    }

    private fun onCityColorClick(cityColor: CityColor) {
        sharedVm.setItem(cityColor)
    }

    @Module
    interface InjectorModule {
        @ContributesAndroidInjector
        fun contributesCityListFragment(): CityListFragment
    }
}