package com.example.map.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.map.ui.MapFragment
import com.example.map.viewmodel.MapViewModel
import com.example.map.viewmodel.MapViewModelImpl
import com.example.newapp.lib.core.injection.ViewModelKey
import com.example.newapp.lib.core.viewmodel.ViewModelFactory
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
