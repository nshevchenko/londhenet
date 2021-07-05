package com.example.newapp.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class
    ]
)
object AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

//    @Provides
//    @Reusable
//    fun provideCoroutineDispatchers(): CoroutineDispatchers =
//        CoroutineDispatchersProvider()

//    @Provides
//    @Reusable
//    fun provideNavDirectionsProvider(): NavDirectionsProvider = NavDirectionsProviderImpl()
//
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
