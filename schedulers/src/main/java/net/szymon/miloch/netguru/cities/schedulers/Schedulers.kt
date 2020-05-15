package net.szymon.miloch.netguru.cities.schedulers

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object Schedulers {
    fun io() = Schedulers.io()
    fun mainThread() = AndroidSchedulers.mainThread()
}