package com.example.realmsampleapp.secure

import android.os.Build
import com.example.realmsampleapp.utils.LogUtils
import java.security.*
import java.security.spec.MGF1ParameterSpec
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource

/**
 * A helper class for secure preferences cipher creation.
 */
internal object SecurePreferencesCipherCreator {

    internal fun createEncryptModeRsaCipher(rsaPublicKey: PublicKey): Cipher? {

        try {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // RSA with OAEP with introduced in Android M, so use that instead if available.
                // We are initializing OAEP in this arcane way because of an Android O specific issue https://issuetracker.google.com/issues/36708951#comment15
                val sp = OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT)
                val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
                cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey, sp)
                cipher
            } else {
                // Before Android M this is the best version of RSA we have available...
                val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey)
                cipher
            }

        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: NoSuchPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidKeyException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidAlgorithmParameterException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        }
    }

    internal fun createDecryptModeRsaCipher(rsaPrivateKey: PrivateKey): Cipher? {

        try {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // RSA with OAEP with introduced in Android M, so use that instead if available.
                // We are initializing OAEP in this arcane way because of an Android O specific issue https://issuetracker.google.com/issues/36708951#comment15
                val sp = OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec("SHA-1"), PSource.PSpecified.DEFAULT)
                val cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")
                cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey, sp)
                cipher
            } else {
                // Before Android M this is the best version of RSA we have available...
                val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
                cipher.init(Cipher.DECRYPT_MODE, rsaPrivateKey)
                cipher
            }

        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: NoSuchPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidKeyException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidAlgorithmParameterException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        }
    }

    internal fun createEncryptModeAesCipher(aesKey: SecretKey, ivParameterSpec: IvParameterSpec): Cipher? {

        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.ENCRYPT_MODE, aesKey, ivParameterSpec)
            return cipher
        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: NoSuchPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidKeyException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidAlgorithmParameterException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        }
    }

    internal fun createDecryptModeAesCipher(aesKey: SecretKey, ivParameterSpec: IvParameterSpec): Cipher? {
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec)
            return cipher
        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: NoSuchPaddingException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidKeyException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: InvalidAlgorithmParameterException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        }
    }
}