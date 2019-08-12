package com.example.realmsampleapp.secure

import java.security.SecureRandom

/**
 * A helper class for loading, generating, and saving the secure preferences IV.
 */
internal object SecurePreferencesIvHelper {

    internal const val AES_IV_BYTE_COUNT = 16
    private val secureRandom = SecureRandom()

    internal fun generateAes256Iv(): ByteArray {
        val ivByteArray = ByteArray(AES_IV_BYTE_COUNT)
        secureRandom.nextBytes(ivByteArray)
        return ivByteArray
    }
}