package com.example.newapp.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newapp.lib.core.injection.ActivityScope
import com.example.newapp.lib.core.injection.ViewModelKey
import com.example.newapp.lib.core.viewmodel.ViewModelFactory
import com.example.newapp.main.MainActivity
import com.example.newapp.main.viewmodel.MainViewModel
import com.example.newapp.main.viewmodel.MainViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MainBindingsModule::class])
internal object MainModule {

    @Provides
    @ActivityScope
    fun provideMainViewModel(
        activity: MainActivity,
        factory: ViewModelFactory
    ): MainViewModel =
        ViewModelProvider(activity, factory).get(MainViewModelImpl::class.java)
}


@Module
internal interface MainBindingsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModelImpl::class)
    fun providesMainViewModelImpl(viewModel: MainViewModelImpl): ViewModel
}
