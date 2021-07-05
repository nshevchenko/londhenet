package com.example.newapp.injection

import android.app.Application
import com.example.newapp.App
import com.ford.fcsdriverinterface.lib.core.injection.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

@ApplicationScope
@Component(
    modules = [
        AppModule::class
//        ConfigModule::class,
//        MainBindings::class,
//        VehicleSearchBindings::class,
//        VinLocationBindings::class,
//        VehicleHistoryBindings::class,
//        SplashBindings::class,
//        HomeBindings::class,
//        ChecklistBindings::class,
//        LoginBindings::class,
//        ReportIssueBindings::class,
//        SettingsBindings::class,
//        ExistingIssuesBindings::class,
//        StandaloneIssueCategoryBindings::class,
//        TermsConditionsBindings::class,
//        VinScannerBindings::class,
//        TermsPrivacyBindings::class,
//        ChecklistDatabaseBuilderModule::class,
//        FileModule::class,
//        TyrePressureBindings::class,
//        LibAnalyticsModule::class,
//        OilLifeBindings::class,
//        LogoutModule::class,
//        UnitsBindings::class,
//        LogbookBindings::class,
//        ExportLogbookBindings::class,
//        RetrofitModule::class,
//        LogbookDetailsBindings::class,
//        LogbookMergeBindings::class,
//        LogbookFilterBindings::class,
//        AnalyticsModule::class
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
