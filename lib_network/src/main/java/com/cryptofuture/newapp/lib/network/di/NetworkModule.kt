package com.cryptofuture.londhenet.lib.network.di

import com.cryptofuture.londhenet.lib.core.injection.ApplicationScope
import com.cryptofuture.londhenet.lib.network.BuildConfig
import com.cryptofuture.londhenet.lib.network.converter.StringConverterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@Module
object NetworkModule {

    @Provides
    @Reusable
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addNetworkInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @Reusable
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @ApplicationScope
    fun provideConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)

    @Provides
    @ApplicationScope
    @Named("string")
    fun provideStringConverterFactory(): Converter.Factory =
        StringConverterFactory()

    @Provides
    @ApplicationScope
    fun provideMoshi() = Moshi.Builder().build()
}
