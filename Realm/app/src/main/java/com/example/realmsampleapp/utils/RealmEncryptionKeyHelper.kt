package com.example.realmsampleapp.utils

import android.util.Base64
import com.example.realmsampleapp.utils.ext.get
import com.example.realmsampleapp.utils.ext.set
import java.security.SecureRandom

fun getRealmEncryptionKey(): ByteArray {
    val keyFromPrefs = getKeyFromPrefs()
    return if (keyFromPrefs.isEmpty()) {
        generateKeyAndSaveToPrefs()
    } else {
        keyFromPrefs
    }
}

private fun getKeyFromPrefs(): ByteArray {
    val key = PreferenceHelper.defaultPrefs[PreferenceHelper.REALM_ENCRYPTION_KEY, ""]
    return Base64.decode(key, Base64.NO_WRAP)
}

private fun generateKeyAndSaveToPrefs(): ByteArray {
    val key = ByteArray(64)
    SecureRandom().nextBytes(key)

    val encodedKey = Base64.encodeToString(key, Base64.NO_WRAP)
    PreferenceHelper.defaultPrefs[PreferenceHelper.REALM_ENCRYPTION_KEY] = encodedKey
    return key
}