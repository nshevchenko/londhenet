package com.example.newapp.di

import android.app.Application
import com.example.login.databinding.FragmentLoginBinding
import com.example.login.di.LoginBindings
import com.example.map.di.MapBindings
import com.example.newapp.App
import com.example.newapp.lib.core.injection.ApplicationScope
import com.example.newapp.lib.network.di.RetrofitModule
import com.example.newapp.main.di.MainBindings
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        RetrofitModule::class,
        MainBindings::class,
        MapBindings::class,
        LoginBindings::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
