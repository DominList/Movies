package com.dpl.dominlist.movies.utlis

import android.util.Log


fun Any.getTAG(): String = this.javaClass.simpleName

private fun Any.getTAG(tag: String?): String = tag?:getTAG()


fun Any.logVerbose(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.v(getTAG(tag), msg, thr)
}

fun Any.logInfo(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.i(getTAG(tag), msg, thr)
}

fun Any.logDebug(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.d(getTAG(tag), msg, thr)
}

fun Any.logWarning(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.w(getTAG(tag), msg, thr)
}

fun Any.logError(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.e(getTAG(tag), msg, thr)
}