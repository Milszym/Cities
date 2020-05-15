package net.szymon.miloch.netguru.cities.base.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

interface HasLifecycleOwner {
    val lifecycleOwner: LifecycleOwner

    fun <T : Any> LiveData<T>.observe(observer: (T) -> Unit) {
        this.observe(lifecycleOwner, Observer {
            it?.let(observer)
        })
    }
}