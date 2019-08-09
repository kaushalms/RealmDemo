package com.example.realmsampleapp.utils

import android.content.SharedPreferences

/**
 * Singleton utility for accessing default preferences.
 */
object PreferenceHelper {
    const val REALM_ENCRYPTION_KEY = "REALM_ENCRYPTION_KEY"

    lateinit var defaultPrefs: SharedPreferences
        private set

    fun init(sharedPreferences: SharedPreferences) {
        defaultPrefs = sharedPreferences
    }
}
