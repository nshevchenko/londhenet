package com.cryptofuture.londhenet.di

import android.app.Application
import android.content.Context
import com.cryptofuture.newapp.lib.core.coroutines.CoroutineDispatchers
import com.cryptofuture.newapp.lib.core.coroutines.CoroutineDispatchersProvider
import com.cryptofuture.londhenet.lib.network.di.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class
    ]
)
object AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @Reusable
    fun provideCoroutineDispatchers(): CoroutineDispatchers =
        CoroutineDispatchersProvider()

//    @Provides
//    @Reusable
//    fun provideNavArgsProvider(): NavArgsProvider = NavArgsProviderImpl()
//
//    @Provides
//    @ApplicationScope
//    fun provideChecklistCache(): ChecklistCache = ChecklistCacheImpl()
//
//    @Provides
//    @ApplicationScope
//    fun provideChecklistCompletedRepository(): ChecklistCompletedRepository = ChecklistCompletedRepositoryImpl()
//
//    @Provides
//    @Reusable
//    fun providesPermissionsManager(): PermissionsManager = PermissionsManagerImpl()
}
