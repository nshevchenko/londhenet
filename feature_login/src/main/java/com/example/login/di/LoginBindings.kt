package com.example.login.di

import com.example.login.ui.LoginFragment
import com.example.newapp.lib.core.injection.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginBindings {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            LoginModule::class
        ]
    )
    abstract fun bindsLoginFragment(): LoginFragment
}
