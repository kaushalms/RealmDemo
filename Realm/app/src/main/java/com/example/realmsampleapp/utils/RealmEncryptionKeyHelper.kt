package com.example.realmsampleapp.utils

import android.app.Application
import com.example.realmsampleapp.secure.SecureKeyLoader
import com.example.realmsampleapp.secure.SecurePreferencesAesKeyStorageImpl
import com.example.realmsampleapp.utils.PreferenceHelper.defaultPrefs

fun getRealmEncryptionKey(application: Application): ByteArray? =
    SecureKeyLoader.getRealmAesKey(
        application,
        SecurePreferencesAesKeyStorageImpl(defaultPrefs)
    )