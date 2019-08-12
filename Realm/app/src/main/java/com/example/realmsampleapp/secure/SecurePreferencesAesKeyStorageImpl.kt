package com.example.realmsampleapp.secure

import android.content.SharedPreferences
import com.example.realmsampleapp.utils.ext.get
import com.example.realmsampleapp.utils.ext.set

internal class SecurePreferencesAesKeyStorageImpl(private val sharedPreferences: SharedPreferences) :
    ISecurePreferencesAesKeyStorage {
    override fun hasAesKey(keyName: String): Boolean {
        return sharedPreferences.contains(keyName)
    }

    override fun storeKey(keyName: String, base64EncodedKeyData: String) {
        sharedPreferences[keyName] = base64EncodedKeyData
    }

    override fun retrieveKey(keyName: String): String? {
        return sharedPreferences[keyName, ""]
    }
}