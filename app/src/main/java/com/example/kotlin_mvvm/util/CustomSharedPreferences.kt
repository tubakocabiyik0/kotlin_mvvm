package com.example.kotlin_mvvm.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import java.lang.Long.getLong

class CustomSharedPreferences {


    companion object {
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private val lock = Any()


        operator fun invoke(context: Context) = Companion.instance ?: synchronized(lock) {
            instance ?: createSharedPref(context).also {
                instance = it
            }
        }

        private fun createSharedPref(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()

        }

    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong("time", time)
        }
    }

    fun getTime() = sharedPreferences?.getLong("time",0)

}