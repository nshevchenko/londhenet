package com.cryptofuture.prediction.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cryptofuture.prediction.ui.PredictionFragment
import com.cryptofuture.londhenet.lib.core.injection.ViewModelKey
import com.cryptofuture.londhenet.lib.core.viewmodel.ViewModelFactory
import com.cryptofuture.prediction.viewmodel.PredictionViewModel
import com.cryptofuture.prediction.viewmodel.PredictionViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MapBindingsModule::class])
internal object PredictionModule {

    @Provides
    fun provideNavController(fragment: PredictionFragment) =
        Navigation.findNavController(fragment.requireView())

    @Provides
    fun provideMapViewModel(
        fragment: PredictionFragment,
        factory: ViewModelFactory
    ): PredictionViewModel = ViewModelProvider(fragment, factory).get(PredictionViewModelImpl::class.java)
}

@Module
internal interface MapBindingsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PredictionViewModelImpl::class)
    fun provideMapViewModelImpl(viewModel: PredictionViewModelImpl): ViewModel

}
