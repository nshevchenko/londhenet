package com.cryptofuture.app.main.di

import com.cryptofuture.londhenet.lib.core.injection.ActivityScope
import com.cryptofuture.app.main.MainActivity
import com.cryptofuture.app.main.di.MainModule
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
