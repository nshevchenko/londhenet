package com.example.newapp

import com.example.newapp.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val bindingModule = DaggerBindingComponent.builder().bindingModule(DataBindingModule)
        bindingModule.application(this).build().run {
            DataBindingUtil.setDefaultComponent(this)
        }
        return DaggerAppComponent.builder().application(this).build().apply { inject(this@App) }
    }

}