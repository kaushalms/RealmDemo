package com.example.realmsampleapp.secure

import android.util.Base64
import com.example.realmsampleapp.utils.LogUtils
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 * A helper class for secure preferences key generation and loading.
 */
internal object SecurePreferencesKeyHelper {

    internal fun generateAndStoreAesKeyIfNecessary(
        aesKeyFileName: String,
        rsaEncryptCipher: Cipher,
        rsaDecryptCipher: Cipher,
        securePreferencesAesKeyStorage: ISecurePreferencesAesKeyStorage,
        keySize: Int
    ): SecretKey? {

        if (securePreferencesAesKeyStorage.hasAesKey(aesKeyFileName)) {
            LogUtils.v(javaClass.simpleName, "Secure Prefs AES Key already exists. Skipping AES key generation.")
            return loadAesKey(aesKeyFileName, rsaDecryptCipher, securePreferencesAesKeyStorage)
        } else {
            LogUtils.v(javaClass.simpleName, "Secure Prefs AES Key does not exist yet. Attempting to generate...")

            try {
                val secretKey = generateAesSecretKey(keySize)

                if (secretKey == null) {
                    LogUtils.w(javaClass.simpleName, "Failed to generate AES key.")
                } else {
                    LogUtils.v(javaClass.simpleName, "Successfully generated AES key.")
                    val encryptedAesKeyBytes =
                        SecurePreferencesEncryption.encryptAesKeyWithRsa(secretKey, rsaEncryptCipher)

                    if (encryptedAesKeyBytes == null || encryptedAesKeyBytes.isEmpty()) {
                        LogUtils.w(javaClass.simpleName, "Failed to encrypt AES key.")
                    } else {
                        LogUtils.v(javaClass.simpleName, "Successfully encrypted AES key.")
                        storeEncryptedAesKey(encryptedAesKeyBytes, aesKeyFileName, securePreferencesAesKeyStorage)
                        return loadAesKey(aesKeyFileName, rsaDecryptCipher, securePreferencesAesKeyStorage)
                    }
                }
            } catch (e: NoSuchAlgorithmException) {
                LogUtils.w(javaClass.simpleName, e)
            }
        }

        return null
    }

    private fun generateAesSecretKey(keySize: Int): SecretKey? {
        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(keySize)
        return keyGenerator.generateKey()
    }

    private fun storeEncryptedAesKey(
        encryptedAesKeyBytes: ByteArray,
        aesKeyFileName: String,
        securePreferencesAesKeyStorage: ISecurePreferencesAesKeyStorage
    ) {
        val base64EncodedEncryptedKey = Base64.encodeToString(encryptedAesKeyBytes, 0)
        LogUtils.v(javaClass.simpleName, "Writing AES Key to file...")
        securePreferencesAesKeyStorage.storeKey(aesKeyFileName, base64EncodedEncryptedKey)
    }

    private fun loadAesKey(
        aesKeyFileName: String,
        rsaDecryptCipher: Cipher,
        securePreferencesAesKeyStorage: ISecurePreferencesAesKeyStorage
    ): SecretKey? {
        LogUtils.v(javaClass.simpleName, "Loading AES Key...")
        val base64EncodedEncryptedKey = securePreferencesAesKeyStorage.retrieveKey(aesKeyFileName)

        if (base64EncodedEncryptedKey == null || base64EncodedEncryptedKey.isEmpty()) {
            LogUtils.w(javaClass.simpleName, "Failure loading AES Key")
        } else {
            LogUtils.v(javaClass.simpleName, "Successfully loaded AES Key...")

            val encryptedAesKeyBytes = Base64.decode(base64EncodedEncryptedKey, 0)
            return SecurePreferencesEncryption.decryptAesKeyWithRsa(encryptedAesKeyBytes, rsaDecryptCipher)
        }

        return null
    }
}
