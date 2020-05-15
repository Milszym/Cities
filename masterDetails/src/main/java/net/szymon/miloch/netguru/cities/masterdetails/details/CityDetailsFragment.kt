package net.szymon.miloch.netguru.cities.masterdetails.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.master_details_toolbar.*
import net.szymon.miloch.netguru.cities.base.BaseFragment
import net.szymon.miloch.netguru.cities.base.ViewModelFactory
import net.szymon.miloch.netguru.cities.logger.Logger
import net.szymon.miloch.netguru.cities.masterdetails.CityMasterDetailsViewModel
import net.szymon.miloch.netguru.cities.masterdetails.R
import net.szymon.miloch.netguru.cities.schedulers.Schedulers
import javax.inject.Inject

class CityDetailsFragment : BaseFragment(), OnMapReadyCallback {

    @Inject
    lateinit var sharedVmFactory: ViewModelFactory<CityMasterDetailsViewModel>
    private lateinit var sharedVm: CityMasterDetailsViewModel

    @Inject
    lateinit var vmFactory: ViewModelFactory<CityDetailsViewModel>
    private lateinit var vm: CityDetailsViewModel

    private var googleMap: GoogleMap? = null

    private var errorDisposable: Disposable? = null

    private val logger = Logger(CityDetailsFragment::class.java.name)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.master_details_city_details_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = vmFactory.get()
        sharedVm = sharedVmFactory.getForActivity()

        observeEvents()
        initializeMap()
    }

    private fun observeEvents() {
        errorDisposable?.dispose()
        errorDisposable = vm.error
            .observeOn(Schedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }, {
                logger.e("Observing error failure", it)
            })
        vm.cityLatLng.observe {
            moveCamera(it)
        }
        sharedVm.cityColorDetails.observe {
            requireActivity().toolbar.apply {
                title = it.city
                setBackgroundColor(it.color)
            }
            vm.setCityColor(it)
        }
    }

    private fun initializeMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.googleMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap
    }

    private fun moveCamera(latLng: LatLng) {
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM))
    }

    override fun onDestroy() {
        super.onDestroy()
        errorDisposable?.dispose()
    }

    @Module
    interface InjectorModule {
        @ContributesAndroidInjector
        fun contributesCityDetailsFragment(): CityDetailsFragment
    }

    companion object {
        private const val DEFAULT_ZOOM = 12f
    }
}