package com.example.realmsampleapp.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.example.realmsampleapp.ui.fragments.UsersFragment
import com.example.realmsampleapp.ui.viewmodel.UsersFragmentViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(
    includes = [
        UserFragmentModule.ProvideViewModel::class
    ]
)
abstract class UserFragmentModule {

    @ContributesAndroidInjector(
        modules = [
            InjectViewModel::class
        ]
    )
    abstract fun bind(): UsersFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(UsersFragmentViewModel::class)
        fun provideViewModel(
        ): ViewModel = UsersFragmentViewModel()
    }

    @Module
    class InjectViewModel {
        @Provides
        fun provideViewModel(
            factory: ViewModelProvider.Factory,
            target: UsersFragment
        ): UsersFragmentViewModel =  ViewModelProviders.of(target, factory).get(UsersFragmentViewModel::class.java)
    }
}
