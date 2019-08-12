package com.example.realmsampleapp.secure

import android.content.SharedPreferences
import com.example.realmsampleapp.utils.LogUtils
import com.example.realmsampleapp.utils.ext.delete
import com.example.realmsampleapp.utils.ext.get
import com.example.realmsampleapp.utils.ext.set
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

/**
 * Singleton utility for access shared preferences and encrypting/decrypting the relevant info.
 */
object SecurePreferencesHelper {

    private lateinit var securePrefs: SharedPreferences
    private var securePrefAesSecretKey: SecretKey? = null

    fun init(
        sharedPreferences: SharedPreferences,
        securePrefAesSecretKey: SecretKey?
    ) {
        this.securePrefs = sharedPreferences
        this.securePrefAesSecretKey = securePrefAesSecretKey
    }

    fun saveSecurely(prefsKey: String, prefValue: String) {
        val key = securePrefAesSecretKey
        val ivParameterSpec = IvParameterSpec(SecurePreferencesIvHelper.generateAes256Iv())

        if (key == null) {
            LogUtils.w(javaClass.simpleName, "AES key was null. Unable to save auth token.")
            return
        }

        val aesEncryptCipher = SecurePreferencesCipherCreator.createEncryptModeAesCipher(key, ivParameterSpec)
        val sha256Hmac = SecurePreferencesEncryption.createSha256Hmac(key)

        if (aesEncryptCipher == null) {
            LogUtils.w(javaClass.simpleName, "AES Encrypt Cipher was null. Unable to save auth token.")
            return
        }

        val encryptedPrefsValue =
            SecurePreferencesEncryption.encryptStringValueWithAes(
                prefValue,
                aesEncryptCipher,
                ivParameterSpec,
                sha256Hmac
            )

        if (encryptedPrefsValue == null) {
            LogUtils.w(javaClass.simpleName, "Error encrypting value for $prefsKey")
        } else {
            securePrefs[prefsKey] = encryptedPrefsValue
        }
    }

    fun getSecurely(prefsKey: String): String? {
        val key = securePrefAesSecretKey

        if (key == null) {
            LogUtils.w(javaClass.simpleName, "AES key was null. Unable to get auth token.")
            return null
        }

        val base64EncodedEncryptedStringValue = securePrefs[prefsKey, ""]

        return if (base64EncodedEncryptedStringValue == null || base64EncodedEncryptedStringValue.isEmpty()) {
            null
        } else {

            val sha256Hmac = SecurePreferencesEncryption.createSha256Hmac(key)
            val encryptionInfo =
                SecurePreferencesDataFormatter.extractEncryptionInfoFromFormattedData(base64EncodedEncryptedStringValue)

            if (encryptionInfo == null) {
                LogUtils.w(javaClass.simpleName, "Encryption info was null. Unable to get auth token.")
                return null
            }

            val aesDecryptCipher =
                SecurePreferencesCipherCreator.createDecryptModeAesCipher(key, IvParameterSpec(encryptionInfo.iv))

            if (aesDecryptCipher == null) {
                LogUtils.w(javaClass.simpleName, "AES Decrypt Cipher was null. Unable to get auth token.")
                null
            } else {
                SecurePreferencesEncryption.decryptStringValueWithAes(
                    aesDecryptCipher,
                    encryptionInfo,
                    sha256Hmac
                )
            }
        }
    }

    fun deleteEntry(prefsKey: String) {
        securePrefs.delete(prefsKey)
    }
}