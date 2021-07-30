package com.example.newapp.main.di

import com.example.newapp.lib.core.injection.ActivityScope
import com.example.newapp.main.MainActivity
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
