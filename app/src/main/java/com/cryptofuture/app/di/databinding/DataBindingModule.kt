package com.cryptofuture.londhenet.di.databinding

import android.app.Application
import android.content.Context
import com.cryptofuture.londhenet.lib.ui.bindingadapters.BindingAdapters
import dagger.Module
import dagger.Provides

//@Module(includes = [AuthModule::class])
@Module
object DataBindingModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @DataBindingScope
    @Provides
    fun providesBindingAdapters() = BindingAdapters()

}
