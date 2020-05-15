package net.szymon.miloch.netguru.cities.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import net.szymon.miloch.netguru.cities.base.lifecycle.HasLifecycleOwner

abstract class BaseActivity : AppCompatActivity(),
    HasLifecycleOwner {

    override val lifecycleOwner: LifecycleOwner
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    inline fun <reified T : ViewModel> ViewModelFactory<T>.get(): T =
        ViewModelProvider(this@BaseActivity, this)[T::class.java]
}