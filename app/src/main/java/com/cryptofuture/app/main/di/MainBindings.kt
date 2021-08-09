package com.cryptofuture.londhenet.main.di

import com.cryptofuture.londhenet.lib.core.injection.ActivityScope
import com.cryptofuture.londhenet.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainBindings {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainModule::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity
}
