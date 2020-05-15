package net.szymon.miloch.netguru.cities.logger

class Logger(private val tag: String) {
    fun d(msg: String): Int {
        println("DEBUG: [$tag]: $msg")
        return 0
    }

    fun e(msg: String, throwable: Throwable): Int {
        println("ERROR: [$tag]: $msg.\n${throwable.stackTrace}")
        return 0
    }
}