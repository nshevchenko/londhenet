package com.example.login.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.login.datasource.LoginService
import com.example.login.ui.LoginFragment
import com.example.login.viewmodel.LoginViewModel
import com.example.login.viewmodel.LoginViewModelImpl
import com.example.newapp.lib.core.injection.ViewModelKey
import com.example.newapp.lib.core.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [LoginBindingsModule::class])
internal object LoginModule {

    @Provides
    fun provideLogbookService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    fun provideNavController(fragment: LoginFragment) =
        Navigation.findNavController(fragment.requireView())

    @Provides
    fun provideLoginViewModel(
        fragment: LoginFragment,
        factory: ViewModelFactory
    ): LoginViewModel = ViewModelProvider(fragment, factory).get(LoginViewModelImpl::class.java)
}

@Module
internal interface LoginBindingsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModelImpl::class)
    fun provideLoginViewModelImpl(viewModel: LoginViewModelImpl): ViewModel
}
