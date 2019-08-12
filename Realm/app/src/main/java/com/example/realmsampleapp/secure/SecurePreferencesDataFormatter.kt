package com.example.realmsampleapp.secure

import android.util.Base64
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import javax.crypto.Mac

internal object SecurePreferencesDataFormatter {

    private const val HMAC_BYTE_SIZE = 32

    internal fun formatEncryptedDataWithIvAndHmac(
        encryptedData: ByteArray,
        iv: ByteArray,
        initializedMac: Mac
    ): String {

        initializedMac.update(iv)
        initializedMac.update(encryptedData)
        val hmacTagBytes = initializedMac.doFinal()

        val baos = ByteArrayOutputStream()
        baos.write(hmacTagBytes)
        baos.write(iv)
        baos.write(encryptedData)

        return Base64.encodeToString(baos.toByteArray(), 0)
    }

    internal fun extractEncryptionInfoFromFormattedData(base64EncodedData: String): EncryptionInfo? {
        val decodedData = Base64.decode(base64EncodedData, 0)
        val byteBuffer = ByteBuffer.wrap(decodedData)

        if (byteBuffer.remaining() >= HMAC_BYTE_SIZE) {
            val hmacTag = ByteArray(HMAC_BYTE_SIZE)
            byteBuffer.get(hmacTag)

            if (byteBuffer.remaining() >= SecurePreferencesIvHelper.AES_IV_BYTE_COUNT) {
                val iv = ByteArray(SecurePreferencesIvHelper.AES_IV_BYTE_COUNT)
                byteBuffer.get(iv)

                if (byteBuffer.remaining() > 0) {
                    val encryptedData = ByteArray(byteBuffer.remaining())
                    byteBuffer.get(encryptedData)

                    return EncryptionInfo(hmacTag, iv, encryptedData)
                }
            }
        }

        return null
    }

    internal fun hasValidHmacTag(encryptionInfo: EncryptionInfo, initializedMac: Mac): Boolean {
        initializedMac.update(encryptionInfo.iv)
        initializedMac.update(encryptionInfo.encryptedData)
        val calculatedHmacTagBytes = initializedMac.doFinal()
        return isEqual(calculatedHmacTagBytes, encryptionInfo.hmacTag)
    }

    /**
     * This function is used for constant time byte comparison.
     */
    private fun isEqual(a: ByteArray, b: ByteArray): Boolean {
        if (a.size != b.size) {
            return false
        }

        var result = 0
        for (i in a.indices) {
            result = result or (a[i].toInt() xor b[i].toInt())
        }
        return result == 0
    }

    internal class EncryptionInfo(val hmacTag: ByteArray, val iv: ByteArray, val encryptedData: ByteArray)
}