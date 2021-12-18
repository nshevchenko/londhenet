package com.cryptofuture.prediction.di

import com.cryptofuture.londhenet.lib.core.injection.FragmentScope
import com.cryptofuture.prediction.ui.PredictionFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PredictionBindings {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [PredictionModule::class]
    )
    abstract fun bindsPredictionFragment(): PredictionFragment
}
