package com.example.realmsampleapp

import android.preference.PreferenceManager
import com.example.realmsampleapp.dagger.DaggerAppComponent
import com.example.realmsampleapp.utils.PreferenceHelper
import com.example.realmsampleapp.utils.getRealmEncryptionKey
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmSampleApplication : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        initPreferences()
        initRealm()
    }

    private fun initRealm() {
        Realm.init(applicationContext)
        val realmConfigurationBuilder = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .name("demo.app")
            .schemaVersion(1)
        getRealmEncryptionKey(this)?.let {
            realmConfigurationBuilder.encryptionKey(it)
        }
        Realm.setDefaultConfiguration(realmConfigurationBuilder.build())
    }

    private fun initPreferences() {
        val defaultPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        PreferenceHelper.init(defaultPreferences)
    }
}