package com.example.realmsampleapp

import com.example.realmsampleapp.dagger.DaggerAppComponent
import dagger.android.DaggerApplication
import io.realm.Realm

class RealmSampleApplication : DaggerApplication() {

    override fun applicationInjector() = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
    }

}