package com.cryptofuture.londhenet.di

import android.app.Application
import com.cryptofuture.login.di.LoginBindings
import com.cryptofuture.prediction.di.MapBindings
import com.cryptofuture.londhenet.App
import com.cryptofuture.londhenet.lib.core.injection.ApplicationScope
import com.cryptofuture.londhenet.lib.network.di.RetrofitModule
import com.cryptofuture.londhenet.main.di.MainBindings
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
