package com.example.realmsampleapp.dagger

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import com.example.realmsampleapp.RealmSampleApplication
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        UiModule::class
    ]
)
interface AppComponent : AndroidInjector<RealmSampleApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RealmSampleApplication): Builder

        fun build(): AppComponent
    }

    override fun inject(app: RealmSampleApplication)
}
