package com.example.realmsampleapp.secure

import android.content.Context
import com.example.realmsampleapp.utils.LogUtils
import com.example.realmsampleapp.utils.ext.hasKey
import java.security.KeyStore
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.SecretKey

/**
 * A helper class for Android Keystore key generation and loading.
 */
object SecureKeyLoader {

    private const val AES_512_KEY_SIZE = 512
    private const val KEYSTORE_ERROR_LOG_MESSAGE = "Unable to load Android KeyStore"

    private const val REALM_RSA_KEY_ALIAS = "REALM_RSA_KEY_ALIAS"
    private const val REALM_AES_KEY_FILE_NAME = "A8684A55130A66AFBDC31A45DD70665C"

    fun getRealmAesKey(context: Context, securePreferencesAesKeyStorage: ISecurePreferencesAesKeyStorage): ByteArray? =
        loadOrCreateEncryptionKeys(
            context,
            securePreferencesAesKeyStorage,
            REALM_RSA_KEY_ALIAS,
            REALM_AES_KEY_FILE_NAME,
            AES_512_KEY_SIZE
        )?.encoded

    private fun loadOrCreateEncryptionKeys(
        context: Context,
        securePreferencesAesKeyStorage: ISecurePreferencesAesKeyStorage,
        keyAlias: String,
        aesKeyFileName: String,
        aesKeySize: Int
    ): SecretKey? {

        val androidKeyStore = AndroidKeystoreHelper.loadAndroidKeyStore()
        generateAndStoreRsaKeyIfNecessary(context, androidKeyStore, keyAlias)

        val rsaPublicKey = loadRsaPublicKey(androidKeyStore, keyAlias)
        val rsaPrivateKey = loadRsaPrivateKey(androidKeyStore, keyAlias)

        when {
            rsaPublicKey == null -> LogUtils.e(javaClass.simpleName, "Unable to load RSA public key. Unable to init.")
            rsaPrivateKey == null -> LogUtils.e(javaClass.simpleName, "Unable to load RSA private key. Unable to init.")
            else -> {
                val rsaEncryptCipher = SecurePreferencesCipherCreator.createEncryptModeRsaCipher(rsaPublicKey)
                val rsaDecryptCipher = SecurePreferencesCipherCreator.createDecryptModeRsaCipher(rsaPrivateKey)

                if (rsaEncryptCipher == null) {
                    LogUtils.e(javaClass.simpleName, "RSA Encrypt Cipher was null. Unable to init.")
                    return null
                } else if (rsaDecryptCipher == null) {
                    LogUtils.e(javaClass.simpleName, "RSA Decrypt Cipher was null. Unable to init.")
                    return null
                }

                return SecurePreferencesKeyHelper.generateAndStoreAesKeyIfNecessary(
                    aesKeyFileName,
                    rsaEncryptCipher,
                    rsaDecryptCipher,
                    securePreferencesAesKeyStorage,
                    aesKeySize
                )
            }
        }

        return null
    }

    private fun generateAndStoreRsaKeyIfNecessary(context: Context, androidKeyStore: KeyStore?, keyName: String) {

        if (androidKeyStore == null) {
            LogUtils.e(javaClass.simpleName, KEYSTORE_ERROR_LOG_MESSAGE)
        } else {

            if (androidKeyStore.hasKey(keyName)) {
                LogUtils.v(javaClass.simpleName, "Secure Prefs RSA Key already exists. Skipping RSA key generation.")
            } else {
                LogUtils.v(javaClass.simpleName, "Secure Prefs RSA Key does not exist yet. Attempting to generate...")
                val didGenerateKey = AndroidKeystoreHelper.generateRsaKey(context, keyName)

                if (didGenerateKey) {
                    LogUtils.v(javaClass.simpleName, "Successfully generated RSA key in AndroidKeyStore.")
                } else {
                    LogUtils.w(javaClass.simpleName, "Failed to generate RSA key in AndroidKeyStore")
                }
            }
        }
    }

    private fun loadRsaPublicKey(androidKeyStore: KeyStore?, keyAlias: String): PublicKey? {
        if (androidKeyStore == null) {
            LogUtils.e(javaClass.simpleName, KEYSTORE_ERROR_LOG_MESSAGE)
        } else {
            return try {
                androidKeyStore.load(null)
                androidKeyStore.getCertificate(keyAlias).publicKey
            } catch (e: IllegalStateException) {
                LogUtils.w(javaClass.simpleName, e)
                null
            }
        }

        return null
    }

    private fun loadRsaPrivateKey(androidKeyStore: KeyStore?, keyAlias: String): PrivateKey? {
        if (androidKeyStore == null) {
            LogUtils.e(javaClass.simpleName, KEYSTORE_ERROR_LOG_MESSAGE)
        } else {
            androidKeyStore.load(null)
            val entry = androidKeyStore.getEntry(keyAlias, null)

            if (entry is KeyStore.PrivateKeyEntry) {
                return entry.privateKey
            }
        }

        return null
    }
}