package com.example.newapp.injection.databinding

import android.app.Application
import androidx.databinding.DataBindingComponent
import com.example.newapp.lib.ui.bindingadapters.BindingAdapters
import dagger.BindsInstance
import dagger.Component

@DataBindingScope
@Component(modules = [DataBindingModule::class])
interface BindingComponent : DataBindingComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindingModule(dataBindingModule: DataBindingModule): Builder

        fun build(): BindingComponent

        @BindsInstance
        fun application(app: Application): Builder
    }

    override fun getBindingAdapters(): BindingAdapters

}
