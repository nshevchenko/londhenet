package com.example.map.di

import com.example.map.ui.MapFragment
import com.example.newapp.lib.core.injection.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MapBindings {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [MapModule::class]
    )
    abstract fun bindsLogbookFragment(): MapFragment
}
