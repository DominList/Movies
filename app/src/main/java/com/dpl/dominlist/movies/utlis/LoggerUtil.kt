@file:Suppress("NOTHING_TO_INLINE")
package com.dpl.dominlist.movies.utlis


import android.util.Log
import kotlin.reflect.KClass

val Any.TAG: String
    get() = extractName(this::class.java)

val Class<*>.TAG: String
    get() = extractName(this)

val KClass<*>.TAG: String
    get() = extractName(this.java)

private fun extractName(clazz: Class<*>): String {
    val name = clazz.simpleName
    return when {
        name.contains("$") -> name.substringBefore("$")
        name.isEmpty() -> "AnonymousClass"
        else -> name
    }
}
@PublishedApi
internal fun Any.resolveTag(tag: Any?): String {
    return when (tag) {
        is String -> tag
        is Class<*> -> tag.TAG
        is KClass<*> -> tag.TAG
        null -> this.TAG
        else -> tag.toString()
    }
}

inline fun Any.logVerbose(msg: String, tag: Any? = null, thr: Throwable? = null) {
    Log.v(resolveTag(tag), msg, thr)
}

inline fun Any.logDebug(msg: String, tag: Any? = null, thr: Throwable? = null) {
    Log.d(resolveTag(tag), msg, thr)
}

inline fun Any.logInfo(msg: String, tag: Any? = null, thr: Throwable? = null) {
    Log.i(resolveTag(tag), msg, thr)
}

inline fun Any.logWarning(msg: String, tag: Any? = null, thr: Throwable? = null) {
    Log.w(resolveTag(tag), msg, thr)
}

inline fun  Any.logError(msg: String, tag: Any? = null, thr: Throwable? = null) {
    Log.e(resolveTag(tag), msg, thr)
}
