package com.cryptofuture.login.di

import com.cryptofuture.login.ui.LoginFragment
import com.cryptofuture.londhenet.lib.core.injection.FragmentScope
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
