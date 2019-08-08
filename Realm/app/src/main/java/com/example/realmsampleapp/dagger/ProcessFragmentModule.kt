package com.example.realmsampleapp.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.example.realmsampleapp.ui.fragments.FavoriteProcessFragment
import com.example.realmsampleapp.ui.viewmodel.FavoriteProcessFragmentViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        ProcessFragmentModule.ProvideViewModel::class
    ]
)
abstract class ProcessFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): FavoriteProcessFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(FavoriteProcessFragmentViewModel::class)
        fun provideViewModel(
        ): ViewModel = FavoriteProcessFragmentViewModel()
    }

    @Module
    class InjectViewModel {
        @Provides
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            target: FavoriteProcessFragment
        ): FavoriteProcessFragmentViewModel =
            ViewModelProviders.of(target, factory).get(FavoriteProcessFragmentViewModel::class.java)
    }
}