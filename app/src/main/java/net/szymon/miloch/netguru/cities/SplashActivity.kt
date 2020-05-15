package net.szymon.miloch.netguru.cities

import android.content.Intent
import android.os.Bundle
import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.reactivex.disposables.Disposable
import net.szymon.miloch.netguru.cities.base.BaseActivity
import net.szymon.miloch.netguru.cities.base.ViewModelFactory
import net.szymon.miloch.netguru.cities.logger.Logger
import net.szymon.miloch.netguru.cities.masterdetails.CityMasterDetailsActivity
import net.szymon.miloch.netguru.cities.schedulers.Schedulers
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory<SplashViewModel>
    private lateinit var vm: SplashViewModel

    private var cityColorDisposable: Disposable? = null

    private val logger = Logger(SplashActivity::class.java.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        vm = vmFactory.get()
        observeCityColor()
    }

    private fun observeCityColor() {
        cityColorDisposable?.dispose()
        cityColorDisposable = vm.cityColor.observeOn(Schedulers.io())
            .subscribeOn(Schedulers.mainThread())
            .subscribe({
                showCities()
                cityColorDisposable?.dispose()
            }, {
                logger.e("Collecting failed", it)
            })
    }

    private fun showCities() {
        startActivity(Intent(this, CityMasterDetailsActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        cityColorDisposable?.dispose()
    }

    @Module
    interface InjectorModule {
        @ContributesAndroidInjector
        fun contributesMainActivity(): SplashActivity
    }
}
