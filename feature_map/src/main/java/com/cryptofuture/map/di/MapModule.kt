package com.cryptofuture.map.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.cryptofuture.map.repository.MapRepository
import com.cryptofuture.map.ui.MapFragment
import com.cryptofuture.map.viewmodel.MapViewModel
import com.cryptofuture.map.viewmodel.MapViewModelImpl
import com.cryptofuture.londhenet.lib.core.injection.ViewModelKey
import com.cryptofuture.londhenet.lib.core.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [MapBindingsModule::class])
internal object MapModule {

    @Provides
    fun provideNavController(fragment: MapFragment) =
        Navigation.findNavController(fragment.requireView())

    @Provides
    fun provideMapViewModel(
        fragment: MapFragment,
        factory: ViewModelFactory
    ): MapViewModel = ViewModelProvider(fragment, factory).get(MapViewModelImpl::class.java)
}

@Module
internal interface MapBindingsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModelImpl::class)
    fun provideMapViewModelImpl(viewModel: MapViewModelImpl): ViewModel

}
