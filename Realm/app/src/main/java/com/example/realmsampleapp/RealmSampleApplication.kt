package com.example.realmsampleapp

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import com.example.realmsampleapp.dagger.DaggerAppComponent
import com.example.realmsampleapp.utils.PreferenceHelper
import dagger.android.DaggerApplication
import io.realm.Realm

class RealmSampleApplication : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        initPreferences()
    }

    private fun initPreferences() {
        val defaultPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        PreferenceHelper.init(defaultPreferences)
    }
}