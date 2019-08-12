package com.example.realmsampleapp.secure

import android.content.Context
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.support.annotation.RequiresApi
import com.example.realmsampleapp.utils.LogUtils
import java.io.IOException
import java.math.BigInteger
import java.security.*
import java.security.cert.CertificateException
import java.util.*
import javax.crypto.KeyGenerator
import javax.security.auth.x500.X500Principal

/**
 * A utility class that helps with loading the AndroidKeyStore as well as generating keys to use inside of it.
 */
internal object AndroidKeystoreHelper {
    private const val ANDROID_KEYSTORE_NAME = "AndroidKeyStore"

    internal fun loadAndroidKeyStore(): KeyStore? {
        val keyStore: KeyStore
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEYSTORE_NAME)
            keyStore.load(null)

        } catch (e: KeyStoreException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: CertificateException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        } catch (e: IOException) {
            LogUtils.w(javaClass.simpleName, e)
            return null
        }

        return keyStore
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    internal fun generateAesSymmetricalKey(keyName: String, userAuthenticationRequired: Boolean): Boolean {

        try {
            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                ANDROID_KEYSTORE_NAME
            )
            keyGenerator.init(
                KeyGenParameterSpec.Builder(keyName, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(userAuthenticationRequired)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build()
            )

            keyGenerator.generateKey()

        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return false
        } catch (e: NoSuchProviderException) {
            LogUtils.w(javaClass.simpleName, e)
            return false
        } catch (e: InvalidAlgorithmParameterException) {
            LogUtils.w(javaClass.simpleName, e)
            return false
        }

        return true
    }

    internal fun generateRsaKey(context: Context, keyName: String): Boolean {

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // On api levels >= 23 we use RSA/ECB/OAEPWithSHA-256.
                // ECB is actually a misnomer here though, as no mode of operations is actually used with RSA.
                generateRsaOaepKey(keyName)
            } else {
                // On api levels lower than 23 we need to use RSA/ECB/PKCS1Padding since OAEP is not available.
                // ECB is actually a misnomer here though, as no mode of operations is actually used with RSA.
                generateRsa2048Key(context, keyName)
            }

        } catch (e: NoSuchAlgorithmException) {
            LogUtils.w(javaClass.simpleName, e)
            return false
        } catch (e: NoSuchProviderException) {
            LogUtils.w(javaClass.simpleName, e)
            return false
        } catch (e: InvalidAlgorithmParameterException) {
            LogUtils.w(javaClass.simpleName, e)
            return false
        }

        return true
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun generateRsaOaepKey(keyName: String) {
        val keyPairGenerator = KeyPairGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_RSA, ANDROID_KEYSTORE_NAME
        )
        keyPairGenerator.initialize(
            KeyGenParameterSpec.Builder(keyName, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setDigests(KeyProperties.DIGEST_SHA256)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .build()
        )
        keyPairGenerator.generateKeyPair()
    }

    private fun generateRsa2048Key(context: Context, keyName: String) {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA", ANDROID_KEYSTORE_NAME)

        val now = Date()
        val endDate = createRsaKeyEndDate(now)

        @Suppress("DEPRECATION")
        keyPairGenerator.initialize(
            android.security.KeyPairGeneratorSpec.Builder(context)
                .setAlias(keyName)
                .setKeySize(2048)
                .setSubject(X500Principal("CN=OrchestratorAndroid,O=UiPath"))
                .setSerialNumber(BigInteger.ONE)
                .setStartDate(now)
                .setEndDate(endDate)
                .build()
        )

        keyPairGenerator.generateKeyPair()
    }

    private fun createRsaKeyEndDate(now: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = now
        calendar.add(Calendar.YEAR, 10)
        return calendar.time
    }
}