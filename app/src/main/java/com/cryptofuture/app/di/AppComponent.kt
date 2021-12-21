package com.cryptofuture.app.di

import android.app.Application
import com.cryptofuture.login.di.LoginBindings
import com.cryptofuture.app.App
import com.cryptofuture.londhenet.di.AppModule
import com.cryptofuture.londhenet.lib.core.injection.ApplicationScope
import com.cryptofuture.londhenet.lib.network.di.RetrofitModule
import com.cryptofuture.app.main.di.MainBindings
import com.cryptofuture.map.di.MapBindings
import com.cryptofuture.prediction.di.PredictionBindings
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
        LoginBindings::class,
        PredictionBindings::class
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
