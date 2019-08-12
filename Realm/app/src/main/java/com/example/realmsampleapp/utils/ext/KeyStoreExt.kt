package com.example.realmsampleapp.utils.ext

import com.example.realmsampleapp.utils.LogUtils
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.UnrecoverableKeyException

fun KeyStore.hasKey(keyName: String): Boolean {
    try {
        val key = getKey(keyName, null)
        return key != null
    } catch (e: NoSuchAlgorithmException) {
        LogUtils.w(javaClass.simpleName, e)
    } catch (e: UnrecoverableKeyException) {
        LogUtils.w(javaClass.simpleName, e)
    } catch (e: KeyStoreException) {
        LogUtils.w(javaClass.simpleName, e)
    }

    return false
}