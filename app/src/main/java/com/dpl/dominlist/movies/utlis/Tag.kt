package com.dpl.dominlist.movies.utlis

import android.util.Log


fun Any.getTAG(): String = this.javaClass.simpleName

private fun Any.getTAG(tag: String?): String = tag?:getTAG()


fun Any.logV(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.v(getTAG(tag), msg, thr)
}

fun Any.logI(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.i(getTAG(tag), msg, thr)
}

fun Any.logD(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.d(getTAG(tag), msg, thr)
}

fun Any.logW(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.w(getTAG(tag), msg, thr)
}

fun Any.logE(tag: String? = null, msg: String, thr: Throwable? = null) {
    Log.e(getTAG(tag), msg, thr)
}