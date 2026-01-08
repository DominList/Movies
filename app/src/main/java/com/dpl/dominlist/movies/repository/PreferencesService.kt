package com.dpl.dominlist.movies.repository

import android.content.Context
import android.icu.util.Calendar
import androidx.core.content.edit
import com.dpl.dominlist.movies.utlis.logDebug
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PreferencesService @Inject constructor(applicationContext: Context) {

    private val sharedPreferences = applicationContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    init {
        logDebug(msg = "PreferencesService: init.")
    }

    fun shouldUpdateDB() = getNowToMillis() - getLastDbUpdateTime() > UPDATE_INTERVAL


    fun markDbUpdated() = setUpdateTime(getNowToMillis())


    private fun getLastDbUpdateTime() : Long  = sharedPreferences.getLong(LAST_MOVIES_DB_UPDATE_TIME_KEY, 0)

    private fun setUpdateTime(time : Long) = sharedPreferences.edit { putLong(LAST_MOVIES_DB_UPDATE_TIME_KEY, time) }
        .apply { logDebug(msg = "setUpdateTime: $time") }


    private companion object {
        // compile time val
        const val PREFERENCES_NAME = "movies_app_preferences"
        const val LAST_MOVIES_DB_UPDATE_TIME_KEY = "last_movies_db_update_time"

        // runtime val
        val UPDATE_INTERVAL : Long = TimeUnit.DAYS.toMillis(1)
    }

}

// should be move to time util
private fun getNowToMillis() = Calendar.getInstance().timeInMillis