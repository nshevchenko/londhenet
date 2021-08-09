package com.cryptofuture.map.di

import com.cryptofuture.map.ui.MapFragment
import com.cryptofuture.londhenet.lib.core.injection.FragmentScope
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
