package com.example.realmsampleapp.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.example.realmsampleapp.ui.MainActivity
import com.example.realmsampleapp.ui.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        MainActivityModule.ProvideViewModel::class
    ]
)
abstract class MainActivityModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule.InjectViewModel::class
        ]
    )
    abstract fun bind(): MainActivity

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun provideMainViewModel(
        ): ViewModel = MainViewModel()
    }

    @Module
    class InjectViewModel {
        @Provides
        fun provideListNotesViewModel(
            factory: ViewModelProvider.Factory,
            target: MainActivity
        ): MainViewModel = ViewModelProviders.of(target, factory).get(MainViewModel::class.java)
    }
}
