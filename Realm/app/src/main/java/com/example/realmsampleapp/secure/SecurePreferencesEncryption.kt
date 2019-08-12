package com.example.realmsampleapp.secure

import com.example.realmsampleapp.utils.LogUtils
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * A helper class for secure preferences encryption and decryption.
 */
internal object SecurePreferencesEncryption {

    internal fun encryptAesKeyWithRsa(aesSecretKey: SecretKey, rsaEncryptCipher: Cipher): ByteArray? {
        val encodedAesKeyBytes = aesSecretKey.encoded
        return try {
            rsaEncryptCipher.doFinal(encodedAesKeyBytes)
        } catch (e: IllegalBlockSizeException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        } catch (e: BadPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        }
    }

    internal fun decryptAesKeyWithRsa(encryptedAesKeyBytes: ByteArray, rsaDecryptCipher: Cipher): SecretKey? {
        LogUtils.v(javaClass.simpleName, "Attempting to decrypt AES Key...")

        return try {
            val encodedAesKeyBytes = rsaDecryptCipher.doFinal(encryptedAesKeyBytes)

            return if (encodedAesKeyBytes.isEmpty()) {
                LogUtils.w(javaClass.simpleName, "Failed to decrypt AES Key...")
                null
            } else {
                LogUtils.v(javaClass.simpleName, "Successfully decrypted AES Key.")
                SecretKeySpec(encodedAesKeyBytes, "AES")
            }
        } catch (e: IllegalBlockSizeException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        } catch (e: BadPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        }
    }

    internal fun encryptStringValueWithAes(
        stringValueToEncrypt: String,
        aesEncryptCipher: Cipher,
        ivParameterSpec: IvParameterSpec,
        initializedMac: Mac
    ): String? {
        return try {
            val encryptedBytes = aesEncryptCipher.doFinal(stringValueToEncrypt.toByteArray())
            return SecurePreferencesDataFormatter.formatEncryptedDataWithIvAndHmac(
                encryptedBytes,
                ivParameterSpec.iv,
                initializedMac
            )
        } catch (e: IllegalBlockSizeException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        } catch (e: BadPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        }
    }

    internal fun decryptStringValueWithAes(
        aesDecryptCipher: Cipher,
        encryptionInfo: SecurePreferencesDataFormatter.EncryptionInfo,
        initializedMac: Mac
    ): String? {
        return try {

            val decryptedData = aesDecryptCipher.doFinal(encryptionInfo.encryptedData)

            if (SecurePreferencesDataFormatter.hasValidHmacTag(encryptionInfo, initializedMac)) {
                String(decryptedData)
            } else {
                LogUtils.w(javaClass.simpleName, "HMAC did not match")
                null
            }
        } catch (e: IllegalBlockSizeException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        } catch (e: BadPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            null
        }
    }

    internal fun createSha256Hmac(aesKey: SecretKey): Mac = Mac.getInstance("HmacSHA256").apply { init(aesKey) }
}