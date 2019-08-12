package com.example.realmsampleapp.secure

interface ISecurePreferencesAesKeyStorage {

    fun hasAesKey(keyName: String): Boolean

    fun storeKey(keyName: String, base64EncodedKeyData: String)

    fun retrieveKey(keyName: String): String?
}