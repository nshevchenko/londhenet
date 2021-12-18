package com.cryptofuture.londhenet.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cryptofuture.londhenet.lib.core.injection.ActivityScope
import com.cryptofuture.londhenet.lib.core.injection.ViewModelKey
import com.cryptofuture.londhenet.lib.core.viewmodel.ViewModelFactory
import com.cryptofuture.app.main.MainActivity
import com.cryptofuture.londhenet.main.viewmodel.MainViewModel
import com.cryptofuture.londhenet.main.viewmodel.MainViewModelImpl
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
